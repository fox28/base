package com.example.day12_ex04;

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

import com.example.day12_ex04.bean.UserBean;
import com.example.day12_ex04.utils.OkImageLoader;
import com.example.day12_ex04.utils.OkUtils;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    static final int ACTION_DOWNLOAD = 1;
    static final int ACTION_PULL_UP = 2;
    static final int ACTION_PULL_DOWN = 3;


    RecyclerView mrvContact;
    SwipeRefreshLayout mSwipeRefreshLayout;
    TextView mtvRefreshHint;

    LinearLayoutManager mManager;
    ArrayList<UserBean> mList;
    ContactAdapter mAdapter;
    int mPageId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mPageId =1;
        downloadContact(mPageId, ACTION_DOWNLOAD);

        setListener();
    }

    private void setListener() {
        setPullDownListener();
        setPullUpListener();
    }

    private void setPullUpListener() {
        mrvContact.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // 获取RecyclerView最后一个列表项的下标
                int lastPosition = mManager.findLastVisibleItemPosition();
                if (lastPosition == mAdapter.getItemCount() - 1 && RecyclerView.SCROLL_STATE_IDLE
                        == newState && mAdapter.isMore() ) {
                    mPageId++;// 指向下一页
                    downloadContact(mPageId, ACTION_PULL_UP);

                }
            }
        });
    }

    private void setPullDownListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                OkImageLoader.release(); // 释放缓存中的图片
                mSwipeRefreshLayout.setRefreshing(true);
                mtvRefreshHint.setVisibility(View.VISIBLE);
                mPageId = 1;
                downloadContact(mPageId, ACTION_PULL_DOWN);
            }
        });
    }

    private void downloadContact(int pageId, final int action) {
        final OkUtils<UserBean[]> utils = new OkUtils<>(this);
        utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST, I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME, "a")
                .addParam(I.PAGE_ID, pageId+"")
                .addParam(I.PAGE_SIZE, "10")
                .targetClass(UserBean[].class)
                .execute(new OkUtils.OnCompleteListener<UserBean[]>() {
                    @Override
                    public void onSuccess(UserBean[] result) {
                        // 判断是否还有数据
                        mAdapter.setIsMore(result != null && result.length > 0);
                        if (!mAdapter.isMore()) {
                            if (action == ACTION_PULL_UP) {
                                mAdapter.setFooterText("没有更多数据");
                            }
                            return;
                        }


                        ArrayList<UserBean> lists = utils.array2List(result);
                        switch (action) {
                            case ACTION_DOWNLOAD:
                                mAdapter.initContactList(lists);
                                break;
                            case ACTION_PULL_DOWN:
                                mAdapter.initContactList(lists);
                                // swipeRefresh操作回复原状
                                mSwipeRefreshLayout.setRefreshing(false);
                                mtvRefreshHint.setVisibility(View.GONE);
                                mAdapter.setFooterText("加载更多");
                                break;
                            case ACTION_PULL_UP:
                                mAdapter.addContactList(lists);

                                break;

                        }

                    }

                    @Override
                    public void onError(String error) {

                    }
                });
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mtvRefreshHint = (TextView) findViewById(R.id.tvRefreshHint);

        mrvContact = (RecyclerView) findViewById(R.id.rvContact);
        mManager = new LinearLayoutManager(this);
        mrvContact.setLayoutManager(mManager);// 加载布局管理器

        mList = new ArrayList<>();
        mAdapter = new ContactAdapter(this, mList);
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
        static final int TYPE_ITEM = 0;
        static final int TYPE_FOOTER = 1;
        Context context;
        ArrayList<UserBean> lists;

        public ContactAdapter(Context context, ArrayList<UserBean> lists) {
            this.context = context;
            this.lists = lists;
        }

        /*
         //是否有更多数据
        boolean isMore;

        public boolean isMore() {
            return isMore;
        }

        public void setMore(boolean more) {
            isMore = more;
        }

         */

        boolean isMore =false;

        public boolean isMore() {
            return isMore;
        }

        public void setIsMore(boolean isMore) {
            this.isMore = isMore;
        }

        String footerText;

        public void setFooterText(String footerText) {
            this.footerText = footerText;
            this.notifyDataSetChanged();
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
                    return new ItemHolder(layout);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder parentHolder, int position) {
            if (position == getItemCount()-1) {
                FooterHolder holder = (FooterHolder) parentHolder;
                holder.tvFooter.setText(footerText);
                holder.tvFooter.setVisibility(View.VISIBLE);
                return;
            }
            ItemHolder holder = (ItemHolder) parentHolder;
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
            return lists != null ? lists.size()+1 : 0;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount()-1) {
                return TYPE_FOOTER;
            }
            return TYPE_ITEM;
        }

        public void initContactList(ArrayList<UserBean> listArr) {
            this.lists.clear();
            this.addContactList(listArr);

        }

        public void addContactList(ArrayList<UserBean> listArr) {
            this.lists.addAll(listArr);
            this.notifyDataSetChanged();
        }
    }


}
