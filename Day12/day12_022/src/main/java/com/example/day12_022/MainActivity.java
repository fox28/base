package com.example.day12_022;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day12_022.bean.UserBean;
import com.example.day12_022.utils.OkImageLoader;
import com.example.day12_022.utils.OkUtils;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int ACTION_DOWNLOAD=1;
    static final int ACTION_PULL_DOWN=2;
    static final int ACTION_PULL_UP=3;
    RecyclerView mrvContact;
    ArrayList<UserBean> mContactList;
    int mPageId;//分页加载的页号
    //布局管理器
    LinearLayoutManager mLayoutManager;
    ContactAdapter mAdapter;

    SwipeRefreshLayout mSwipeRefreshLayout;
    TextView mtvRefreshHint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mPageId=1;
        downloadContactList(mPageId,ACTION_DOWNLOAD);

        setListener();
    }

    private void setListener() {
        mSwipeRefreshLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 释放内存中缓存的头像
                OkImageLoader.release();
                mSwipeRefreshLayout.setRefreshing(true);
                mPageId = 1;
                downloadContactList(mPageId, ACTION_PULL_DOWN);
            }
        });

    }


    /**
     * 从服务端下载联系人的json数据，结果是ArrayList<UserBean>
     * @param pageId:页号
     * @param action：下载数据的动作：
     */
    private void downloadContactList(int pageId, final int action) {
        final OkUtils<UserBean[]> utils = new OkUtils<>(this);
        utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST,I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME,"a")
                .addParam(I.PAGE_ID,pageId+"")
                .addParam(I.PAGE_SIZE,"10")
                .targetClass(UserBean[].class)
                .execute(new OkUtils.OnCompleteListener<UserBean[]>() {
                    @Override
                    public void onSuccess(UserBean[] result) {
                        ArrayList<UserBean> contactList = utils.array2List(result);

                        switch (action) {
                            case ACTION_DOWNLOAD:
                                mAdapter.initContactList(contactList);
                                break;
                            case ACTION_PULL_DOWN:
                                mAdapter.initContactList(contactList);
                                mSwipeRefreshLayout.setRefreshing(false);
                                mtvRefreshHint.setVisibility(View.GONE);
                                break;
                            case ACTION_PULL_UP:
                                break;
                        }





                    }

                    @Override
                    public void onError(String error) {

                    }
                });

    }

    private void initView() {
        // 下拉菜单增加
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
        mtvRefreshHint = (TextView) findViewById(R.id.tvRefreshHint);

        mrvContact = (RecyclerView) findViewById(R.id.rvContact);
        mLayoutManager = new LinearLayoutManager(this);
        mrvContact.setLayoutManager(mLayoutManager);

        mContactList = new ArrayList<>();
        mAdapter = new ContactAdapter(this, mContactList);
        mrvContact.setAdapter(mAdapter);
    }

    class FooterHolder extends RecyclerView.ViewHolder {
        TextView tvFooter;
        public FooterHolder(View itemView) {
            super(itemView);
            tvFooter = (TextView) itemView.findViewById(R.id.tvFooter);
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar;
        TextView tvUserName,tvNick;
        public ItemHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvNick = (TextView) itemView.findViewById(R.id.tvNick);
        }
    }

    class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        static final int TYPE_FOOTER=0;
        static final int TYPE_ITEM=1;
        Context context;
        ArrayList<UserBean> contactList;

        String footerText;

        public void setFooterText(String footerText) {
            this.footerText = footerText;
        }

        public ContactAdapter(Context context, ArrayList<UserBean> contactList) {
            this.context = context;
            this.contactList = contactList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View layout=null;
            switch (viewType) {
                case TYPE_FOOTER:
                    layout = View.inflate(context, R.layout.item_footer, null);
                    return new FooterHolder(layout);
                case TYPE_ITEM:
                    layout = View.inflate(context, R.layout.item_contact, null);
                    return new ItemHolder(layout);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder parentHolder, int position) {
            if (getItemViewType(position) == TYPE_FOOTER) {
                FooterHolder holder= (FooterHolder) parentHolder;
                holder.tvFooter.setVisibility(View.VISIBLE);
                holder.tvFooter.setText(footerText);
                return;
            }
            ItemHolder holder= (ItemHolder) parentHolder;
            UserBean user = contactList.get(position);
            holder.tvUserName.setText(user.getUserName());
            holder.tvNick.setText(user.getNick());

            OkImageLoader.build(I.REQUEST_DOWNLOAD_AVATAR_URL+user.getUserName())
                    .defaultPicture(R.drawable.default_face)
                    .imageView(holder.ivAvatar)
                    .showImage(context);
        }

        @Override
        public int getItemCount() {
            return contactList.size()+1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount() - 1) {
                return TYPE_FOOTER;
            }
            return TYPE_ITEM;
        }

        public void initContactList(ArrayList<UserBean> contactList) {
            this.contactList.clear();
            this.contactList.addAll(contactList);
            notifyDataSetChanged();
        }
    }

}
