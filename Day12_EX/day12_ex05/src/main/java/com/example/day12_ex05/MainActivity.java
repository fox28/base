package com.example.day12_ex05;

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

import com.example.day12_ex05.bean.UserBean;
import com.example.day12_ex05.utils.OkImageLoader;
import com.example.day12_ex05.utils.OkUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int ACTION_DOWNLOAD = 1;
    static final int ACTION_PULL_DOWN = 2;
    static final int ACTION_PULL_UP = 3;

    RecyclerView mrv_goods;
    SwipeRefreshLayout mSwipeRefreshLayout;
    TextView mtvRefresh;
    LinearLayoutManager mManager;
    int mPageId;

    ContactAdapter mAdapter;
    ArrayList<UserBean> mList;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mPageId = 1;
        downloadContactList(mPageId, ACTION_DOWNLOAD);
        setListener();

    }

    private void setListener() {
        setPullDownListener();
        setPullUpListener();
    }

    private void setPullDownListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                OkImageLoader.release();
                // R.id.tvRefresh 设置为可见
                mtvRefresh.setVisibility(View.VISIBLE);
                mSwipeRefreshLayout.setRefreshing(true);
                mPageId = 1;
                downloadContactList(mPageId, ACTION_PULL_DOWN);
            }
        });
    }

    private void setPullUpListener() {
        mrv_goods.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                // 获得recyclerView最后一个列表项的下标
                int lastPosition = mManager.findLastVisibleItemPosition();
                if (lastPosition == mManager.getItemCount() - 1 && newState == recyclerView.SCROLL_STATE_IDLE
                        && mAdapter.isMore() ) {
                    // 提交下一页的下载申请
                    mPageId++;
                    downloadContactList(mPageId, ACTION_PULL_UP);
                }


            }
        });
    }

    private void downloadContactList(int pageId, final int action) {
        final OkUtils<UserBean[]> utils = new OkUtils<>(this);
        utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST, I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME, "a")
                .addParam(I.PAGE_ID, pageId + "")
                .addParam(I.PAGE_SIZE, "10")
                .targetClass(UserBean[].class)
                .execute(new OkUtils.OnCompleteListener<UserBean[]>() {
                    @Override
                    public void onSuccess(UserBean[] result) {
                        mAdapter.setMore(result != null && result.length>0);
                        if (! mAdapter.isMore()) {
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

                                // R.id.tvRefresh 设置为可见 Refresh关闭
                                mSwipeRefreshLayout.setRefreshing(false);
                                mtvRefresh.setVisibility(View.GONE);

                                break;
                            case ACTION_PULL_UP:
                                mAdapter.addContactList(lists);
                                mAdapter.setFooterText("加载更多数据");
                                break;

                        }

                    }

                    @Override
                    public void onError(String error) {

                    }
                });
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_srl);
        mtvRefresh = (TextView) findViewById(R.id.tvRefresh);

        mrv_goods = (RecyclerView) findViewById(R.id.rv_goods);
        mManager = new LinearLayoutManager(this);
        mrv_goods.setLayoutManager(mManager);

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

    class ContactHolder extends RecyclerView.ViewHolder {
        TextView tvUserName, tvNick;
        ImageView ivAvatar;

        public ContactHolder(View itemView) {
            super(itemView);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvNick = (TextView) itemView.findViewById(R.id.tvNick);
            ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
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

        boolean isMore;

        public boolean isMore() {
            return isMore;
        }

        public void setMore(boolean more) {
            isMore = more;
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
                case TYPE_ITEM:
                    ContactHolder holder = new ContactHolder(View.inflate(context, R.layout.item_contact, null));
                    return holder;
                case TYPE_FOOTER:
                    layout = View.inflate(context, R.layout.item_footer, null);
                    return new FooterHolder(layout);
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
            return lists.size() + 1;
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
