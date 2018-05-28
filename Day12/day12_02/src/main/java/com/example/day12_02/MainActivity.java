package com.example.day12_02;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day12_02.bean.UserBean;
import com.example.day12_02.utils.OkImageLoader;
import com.example.day12_02.utils.OkUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int ACTION_DOWNLOAD = 1;
    static final int ACTION_PULL_DOWN = 2;
    static final int ACTION_PULL_UP = 3;
    RecyclerView mrvContact;
    ArrayList<UserBean> mContactList;
    int mPageId;
    LinearLayoutManager mLayoutManager;
    ContactAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPageId=1;
        downloadContactList(mPageId, ACTION_DOWNLOAD);
        initView();


    }

    /**
     * 从服务端下载联系人的json数据，结果是ArrayList<Userbean>
     * @param pageId
     * @param action
     */
    private void downloadContactList(int pageId, int action) {
        final OkUtils<UserBean[]> utils = new OkUtils<>(this);
        utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST, I.REQUEST_DOWNLOAD_CONTACT_LIST)       // 下载类型
                .addParam(I.User.USER_NAME, "a")
                .addParam(I.PAGE_ID, pageId+"")
                .addParam(I.PAGE_SIZE,"10")   // 每页数量
                .targetClass(UserBean[].class)
                .execute(new OkUtils.OnCompleteListener<UserBean[]>() {
                    @Override
                    public void onSuccess(UserBean[] result) {
                        ArrayList<UserBean> contactList = utils.array2List(result);
                        mAdapter.initContactList(contactList);
                    }

                    @Override
                    public void onError(String error) {

                    }
                });


    }

    private void initView() {
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
    class ContactHolder extends  RecyclerView.ViewHolder {
        ImageView ivAvatar;
        TextView tvUserName, tvNick;
        public ContactHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvNick = (TextView) itemView.findViewById(R.id.tvNick);
        }
    }

    class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        static final int TYPE_FOOTER = 0;
        static final int TYPE_CONTACT =1;
        Context context;
        ArrayList<UserBean> contactList;

        public ContactAdapter(Context context, ArrayList<UserBean> contactList) {
            this.context = context;
            this.contactList = contactList;
        }

        String footerText;

        public void setFooterText(String footerText) {
            this.footerText = footerText;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType) {
                case TYPE_FOOTER:
                    view = View.inflate(context, R.layout.item_footer, null);
                    return new FooterHolder(view);
                case TYPE_CONTACT:
                    view = View.inflate(context, R.layout.contact_item, null);
                    return new ContactHolder(view);

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
            ContactHolder holder = (ContactHolder) parentHolder;
            UserBean user = contactList.get(position);
            holder.tvUserName.setText(user.getUserName());
            holder.tvNick.setText(user.getNick());

            // 显示缩略图
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
            return TYPE_CONTACT;
        }

        public void initContactList(ArrayList<UserBean> contactList) {
            this.contactList.clear();
            this.contactList.addAll(contactList);
            notifyDataSetChanged();
        }
    }




}
