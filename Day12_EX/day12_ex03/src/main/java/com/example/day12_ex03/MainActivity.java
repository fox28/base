package com.example.day12_ex03;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day12_ex03.bean.UserBean;
import com.example.day12_ex03.utils.OkImageLoader;
import com.example.day12_ex03.utils.OkUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int ACTION_DOWNLOAD = 1;
    static final int ACTION_PULL_DOWN = 2;
    static final int ACTION_PULL_UP = 3;


    RecyclerView mrvContact;
    LinearLayoutManager manager ;
    ArrayList<UserBean> mList;
    ContactAdapter mAdapter;

    int mPageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mPageId=1;
        initData(mPageId, ACTION_DOWNLOAD);

    }

    private void initData(int pageId, int action) {
        final OkUtils<UserBean[]> utils = new OkUtils<>(this);
        utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST,I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME, "a")
                .addParam(I.PAGE_ID, pageId+"")
                .addParam(I.PAGE_SIZE, "10")
                .targetClass(UserBean[].class)
                .execute(new OkUtils.OnCompleteListener<UserBean[]>() {
                    @Override
                    public void onSuccess(UserBean[] result) {
                        ArrayList<UserBean> lists = utils.array2List(result);
                        mAdapter.initContactList(lists);
                    }

                    @Override
                    public void onError(String error) {
                        Log.i("main", "下载失败" + error);

                    }
                });
    }

    private void initView() {
        mrvContact = (RecyclerView) findViewById(R.id.rvContact);
        manager = new LinearLayoutManager(this);
        mrvContact.setLayoutManager(manager);

        mList = new ArrayList<>();
        mAdapter = new ContactAdapter(this, mList);
        mrvContact.setAdapter(mAdapter);


    }

    /**
     * 创建item_footer.xml的布局加载器
     */
    class FooterHolder extends RecyclerView.ViewHolder {
        TextView tvFooter;
        public FooterHolder(View itemView) {
            super(itemView);
            tvFooter = (TextView) itemView.findViewById(R.id.tvFooter);
        }
    }

    class ContactHolder extends RecyclerView.ViewHolder {
        TextView tvUserName, tvNick;
        ImageView ivAvatar;
        public ContactHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            tvNick = (TextView) itemView.findViewById(R.id.tvNick);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
        }
    }

    class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        static final int TYPE_ITEM = 0;
        static final int TYPE_FOOTER = 1;

        Context context;
        ArrayList<UserBean> lists;

        public ContactAdapter(Context context, ArrayList<UserBean> lists) {
            this.context = context;
            this.lists = lists;
        }

        String footerText;

        public void setFooterText(String footerText) {
            this.footerText = footerText;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View layout;
            switch (viewType) {
                case TYPE_FOOTER:
                    layout = View.inflate(context, R.layout.item_footer, null);
                    return new FooterHolder(layout);
                case TYPE_ITEM:
                    layout = View.inflate(context, R.layout.item_contact, null);
                    return new ContactHolder(layout);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder parentHolder, int position) {
            if (getItemViewType(position) == TYPE_FOOTER) {
                FooterHolder holder = (FooterHolder) parentHolder;
                holder.tvFooter.setText("测试");
                holder.tvFooter.setVisibility(View.VISIBLE);
                return;
            }
            ContactHolder holder = (ContactHolder) parentHolder;
            UserBean bean = lists.get(position);
            holder.tvUserName.setText(bean.getUserName());
            holder.tvNick.setText(bean.getNick());

            OkImageLoader.build(I.REQUEST_DOWNLOAD_AVATAR_URL)
                    .defaultPicture(R.drawable.default_face)
                    .imageView(holder.ivAvatar)
                    .showImage(context);
        }

        @Override
        public int getItemCount() {
            return lists.size()+1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount() - 1) {
                return TYPE_FOOTER;
            }
            return TYPE_ITEM;
        }


        public void initContactList(ArrayList<UserBean> lists) {
            this.lists.clear();
            this.lists.addAll(lists);
            this.notifyDataSetChanged();
        }
    }



}
