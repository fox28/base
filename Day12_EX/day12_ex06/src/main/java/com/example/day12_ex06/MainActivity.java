package com.example.day12_ex06;

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

import com.example.day12_ex06.bean.UserBean;
import com.example.day12_ex06.utils.OkImageLoader;
import com.example.day12_ex06.utils.OkUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int ACTION_DOWNLOAD = 1;
    static final int ACTION_PULL_DOWN = 2;
    static final int ACTION_PULL_UP= 3;

    RecyclerView mrv_goods;
    LinearLayoutManager mManager;
    SwipeRefreshLayout mSwipeRefreshLayout;
    TextView mtvRefresh;
    int mPageId;
    ContactAdapter mAdapter;
    ArrayList<UserBean> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mPageId = 1;
        loadContactList(mPageId, ACTION_DOWNLOAD);
        setListener();

    }

    private void setListener() {
        setPullDownListener();
        setPullUpListener();

    }

    private void setPullUpListener() {
        mrv_goods.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // 获得recyclerView的最后一个列表项的下标
                int lastPosition = mManager.findLastVisibleItemPosition();
                if (lastPosition == mAdapter.getItemCount()-1 && mAdapter.isMore()
                        && recyclerView.SCROLL_STATE_IDLE == newState) {

                    mPageId++;
                    loadContactList(mPageId, ACTION_PULL_UP);
                }
            }
        });
    }

    private void setPullDownListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                OkImageLoader.release();// 释放已下载的图片
                mSwipeRefreshLayout.setRefreshing(true);
                mtvRefresh.setVisibility(View.GONE);
                mPageId = 1;
                loadContactList(mPageId, ACTION_PULL_DOWN);
            }
        });
    }

    private void loadContactList(int pageId, final int action) {
        final OkUtils<UserBean[]> utils = new OkUtils<>(this);
        utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST,I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME, "a")
                .addParam(I.PAGE_ID, pageId+"")
                .addParam(I.PAGE_ID, "10")
                .targetClass(UserBean[].class)
                .execute(new OkUtils.OnCompleteListener<UserBean[]>() {
                    @Override
                    public void onSuccess(UserBean[] result) {
                        mAdapter.setMore(result != null && result.length>0);
                        if (!mAdapter.isMore()) {
                            if (action == ACTION_PULL_UP) {
                                mAdapter.setFooterText("没有更多数据");
                            }
                            return;
                        }

                        ArrayList<UserBean> listArr = utils.array2List(result);
                        switch (action) {
                            case ACTION_DOWNLOAD:
                                mAdapter.initContactList(listArr);
                                break;
                            case ACTION_PULL_DOWN:
                                mAdapter.initContactList(listArr);
                                mSwipeRefreshLayout.setRefreshing(false);
                                mtvRefresh.setVisibility(View.GONE);
                                break;
                            case ACTION_PULL_UP:
                                mAdapter.addContactList(listArr);
                                break;
                        }
                    }

                    @Override
                    public void onError(String error) {

                    }
                });

    }

    private void initView() {
        mrv_goods = (RecyclerView) findViewById(R.id.rv_goods);
        mManager = new LinearLayoutManager(this);
        mrv_goods.setLayoutManager(mManager);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_srl);
        mtvRefresh = (TextView) findViewById(R.id.tvRefresh);

        mList = new ArrayList<>();
        mAdapter = new ContactAdapter(this, mList);
        mrv_goods.setAdapter(mAdapter);



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
            this.notifyDataSetChanged();
        }

        boolean isMore;

        public boolean isMore() {
            return isMore;
        }

        public void setMore(boolean more) {
            isMore = more;
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
            if (getItemViewType(position) == TYPE_FOOTER) {
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
            return lists != null? lists.size()+1 : 0;

        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount() - 1) {
                return TYPE_FOOTER;
            }
            return TYPE_ITEM;
        }

        public void initContactList(ArrayList<UserBean> listArr) {
            this.lists.clear();
            this.lists.addAll(listArr);
            this.notifyDataSetChanged();
        }

        public void addContactList(ArrayList<UserBean> listArr) {
            this.lists.addAll(listArr);
            this.notifyDataSetChanged();
        }
    }

}

