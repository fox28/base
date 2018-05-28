package com.example.day12_ex01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day12_ex01.bean.UserBean;
import com.example.day12_ex01.utils.OkImageLoader;
import com.example.day12_ex01.utils.OkUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int ACTION_DOWNLOAD = 1;
    static final int ACTION_PULL_DOWN = 2;
    static final int ACTION_PULL_UP = 3;
    RecyclerView mrvContact;
    ArrayList<UserBean> mContactList;
    int mPageId; // 分页加载的页号
    LinearLayoutManager mLayoutManager;

    ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mPageId = 1;
        downloadContactList(mPageId, ACTION_DOWNLOAD);
    }

    /**
     * 从服务端下载联系人的json数据，结果是ArrayList<UserBean>
     * @param pageId 页号
     * @param action 下载数据的动作
     */
    private void downloadContactList(int pageId, int action) {
        final OkUtils<UserBean[]> utils = new OkUtils<>(this);
        utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST, I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME,"a")
                .addParam(I.PAGE_ID,pageId+"")
                .addParam(I.PAGE_SIZE, "10")
                .targetClass(UserBean[].class)
                .execute(new OkUtils.OnCompleteListener<UserBean[]>() {
                    @Override
                    public void onSuccess(UserBean[] result) {
                        ArrayList<UserBean> contactList = utils.array2List(result);
                        mAdapter.initContactList(contactList);
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(MainActivity.this, "下载失败", Toast.LENGTH_LONG).show();

                    }
                });
    }

    private void initView() {
        mrvContact = (RecyclerView) findViewById(R.id.rvContact);
        mLayoutManager = new LinearLayoutManager(this);
        mrvContact.setLayoutManager(mLayoutManager);

        mContactList = new ArrayList<>();
        mAdapter = new ContactAdapter(this, mContactList);
        mAdapter.setFooterText("加载更多");
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
        TextView tvUserName, tvNick;


        public ItemHolder(View itemView) {
            super(itemView);

            ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvNick = (TextView) itemView.findViewById(R.id.tvNick);
        }
    }

    class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        static final int TYPE_FOOTER= 0;
        static final int TYPE_ITEM = 1;
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
            View layout = null;
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
                FooterHolder holder = (FooterHolder) parentHolder;
                holder.tvFooter.setVisibility(View.VISIBLE);
                holder.tvFooter.setText(footerText);
                return;
            }
            ItemHolder holder = (ItemHolder) parentHolder;
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
