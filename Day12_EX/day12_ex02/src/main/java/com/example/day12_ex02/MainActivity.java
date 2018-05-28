package com.example.day12_ex02;

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

import com.example.day12_ex02.bean.UserBean;
import com.example.day12_ex02.utils.OkImageLoader;
import com.example.day12_ex02.utils.OkUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int ACTION_DOWNLOAD = 0;
    static final int ACTION_PULL_DOWN = 1;
    static final int ACTION_PULL_UP = 2;

    RecyclerView mrvContact;
    LinearLayoutManager manager;
    ArrayList<UserBean> mList;
    ContactAdapter mAdapter;
    int mPageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mPageId = 1;
        downloadContactList(mPageId, ACTION_DOWNLOAD);

    }

    private void downloadContactList(int pageId, int actionDownload) {
        final OkUtils<UserBean[]> utils = new OkUtils<>(this);
        utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST, I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME,"a")
                .addParam(I.PAGE_ID, pageId+"")
                .addParam(I.PAGE_SIZE,10+"")
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
        mAdapter = new ContactAdapter(mList, this);
        mrvContact.setAdapter(mAdapter);


    }

    class FooterHolder extends RecyclerView.ViewHolder {
        TextView tvFooter;
        public FooterHolder(View itemView) {
            super(itemView);
            tvFooter = (TextView) itemView.findViewById(R.id.tvFooter);
        }
    }

    class ContactHolder extends RecyclerView.ViewHolder {
        TextView tvUserName, tvUserNick;
        ImageView ivAvatar;
        public ContactHolder(View itemView) {
            super(itemView);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvUserNick = (TextView) itemView.findViewById(R.id.tvUserNick);
            ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatar);

        }
    }

    class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        static final int TYPE_ITEM = 0;
        static final int TYPE_FOOTER = 1;

        ArrayList<UserBean> list;
        Context context;

        public ContactAdapter(ArrayList<UserBean> list, Context context) {
            this.list = list;
            this.context = context;
        }

        String textFooter;

        public void setTextFooter(String textFooter) {
            this.textFooter = textFooter;
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
                this.setTextFooter("加载加载测试");
                holder.tvFooter.setText(textFooter);
                holder.tvFooter.setVisibility(View.VISIBLE);
                return;
            }
            ContactHolder holder = (ContactHolder) parentHolder;
            UserBean bean = list.get(position);
            holder.tvUserNick.setText(bean.getNick());
            holder.tvUserName.setText(bean.getUserName());

            OkImageLoader.build(I.REQUEST_DOWNLOAD_AVATAR_URL+bean.getUserName())
                    .defaultPicture(R.drawable.default_face)
                    .imageView(holder.ivAvatar)
                    .showImage(context);
        }

        @Override
        public int getItemCount() {
            return list != null?list.size()+1:0;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount() - 1) {
                return TYPE_FOOTER;
            }
            return TYPE_ITEM;
        }


        public void initContactList(ArrayList<UserBean> lists) {
            mList.clear();
            mList.addAll(lists);
            mAdapter.notifyDataSetChanged();
        }
    }



}
