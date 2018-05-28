package com.example.day11_05;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day11_05.bean.AppBean;
import com.example.day11_05.dao.AppDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<AppBean> mArrayList;
    RecyclerView mrvApp;
    AppAdapter mAdapter;

    ImageView mivMoveBottom, mivMoveTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        setListener();
    }

    /**
     * 设置监听事件：置顶，快速到底部
     */
    private void setListener() {
        mivMoveBottom = (ImageView) findViewById(R.id.ivMoveBottom);
        mivMoveBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mrvApp.scrollToPosition(mAdapter.getItemCount() - 1);
            }
        });

        mivMoveTop = (ImageView) findViewById(R.id.ivMoveTop);
        mivMoveTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mrvApp.scrollToPosition(0);
            }
        });

    }


    private void initView() {
        mrvApp = (RecyclerView) findViewById(R.id.rvApp);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // 设置布局管理器
        mrvApp.setLayoutManager(layoutManager);

        mAdapter = new AppAdapter(this, mArrayList);

        // 设置mrvApp与指定的适配器关联
        mrvApp.setAdapter(mAdapter);

    }

    private void initData() {
        mArrayList = AppDao.getData(this);
    }


    /**
     * 将app_item.xml解析为java对象
     */
    class AppViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        ImageView ivDelte;
        ImageView ivUpdate;
        TextView tvName;
        TextView tvVersion;
        TextView tvFileSize;



        public AppViewHolder(View itemView) {
            super(itemView);
            ivThumb = (ImageView) itemView.findViewById(R.id.ivThumb);
            ivDelte = (ImageView) itemView.findViewById(R.id.ivDelete);
            ivUpdate = (ImageView) itemView.findViewById(R.id.ivUpdate);

            tvName = (TextView) itemView.findViewById(R.id.tvAppName);
            tvVersion = (TextView) itemView.findViewById(R.id.tvAppVersion);
            tvFileSize = (TextView) itemView.findViewById(R.id.tvAppFileSize);
        }
    }

    /**
     * 将item_footer.xml解析为java对象
     */
    class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView tvFooter;

        public FooterViewHolder(View itemView) {
            super(itemView);
            tvFooter = (TextView) itemView.findViewById(R.id.tvFooter);
        }
    }

    /**
     * 定义适配器，将数据集显示在列表项中
     * 为RecyclerView提供指定类型的数据集的绑定
     * 绑定的ViewHolder对象
     */
    class AppAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        final static int TYPE_ITEM = 0;
        final static int TYPE_FOOTER = 1;
        Context context;
        ArrayList<AppBean> arrayList;

        public AppAdapter(Context context, ArrayList<AppBean> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            switch (viewType) {
                case TYPE_ITEM:
                    return new AppViewHolder(inflater.inflate(R.layout.app_item, null));
                case TYPE_FOOTER:
                    return new FooterViewHolder(inflater.inflate(R.layout.item_footer, null));

            }

            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder parentHolder, final int position) {
            if (getItemViewType(position) == TYPE_FOOTER) {
                FooterViewHolder holder = (FooterViewHolder) parentHolder;
                holder.tvFooter.setVisibility(View.VISIBLE);
                return;
            }
            AppViewHolder holder = (AppViewHolder) parentHolder;
            AppBean app = arrayList.get(position);
            holder.ivThumb.setImageResource(app.getPhotoId());
            holder.tvName.setText(app.getName());
            holder.tvVersion.setText(app.getVersion());
            holder.tvFileSize.setText(app.getFileSize()+"kb");

            // 删除数据项
            holder.ivDelte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrayList.remove(position);
                    notifyDataSetChanged();// 通知刷新适配器
                }
            });

            // 单击编辑按钮，显示说明信息
            holder.ivUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, arrayList.get(position).getIntro(),
                            Toast.LENGTH_SHORT).show();
                }
            });

        }



        @Override
        public int getItemCount() {
            return arrayList.size()+1;
        }

        // 获取列表项的类型
        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount() -1) {
                return TYPE_FOOTER;
            }
            return  TYPE_ITEM;
        }


    }



}
