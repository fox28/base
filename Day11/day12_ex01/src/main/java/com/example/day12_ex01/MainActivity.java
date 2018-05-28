package com.example.day12_ex01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day12_ex01.bean.AppBean;
import com.example.day12_ex01.dao.AppDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView mrvApp;
    ArrayList<AppBean> mArrayList;
    AppAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView() {
        mrvApp = (RecyclerView) findViewById(R.id.rvApp);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mrvApp.setLayoutManager(layoutManager);

        mAdapter = new AppAdapter(this, mArrayList);
        mrvApp.setAdapter(mAdapter);


    }

    private void initData() {
        mArrayList = AppDao.getData(this);
    }

    class AppViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb, ivDelete, ivUpdate;
        TextView tvName, tvVersion, tvFileSize;
        public AppViewHolder(View itemView) {
            super(itemView);
            ivThumb = (ImageView) itemView.findViewById(R.id.ivThumb);
            ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);
            ivUpdate = (ImageView) itemView.findViewById(R.id.ivUpdate);

            tvName = (TextView) itemView.findViewById(R.id.tvAppName);
            tvVersion = (TextView) itemView.findViewById(R.id.tvAppVersion);
            tvFileSize = (TextView) itemView.findViewById(R.id.tvAppFileSize);

        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView tvFooter;
        public FooterViewHolder(View itemView) {
            super(itemView);
            tvFooter = (TextView) findViewById(R.id.tvFooter);
        }
    }

    class AppAdapter extends RecyclerView.Adapter {
        static final int TYPE_ITEM = 0;
        static final int TYPE_FOOTER = 1;
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
                case TYPE_FOOTER:
                    View view = inflater.inflate(R.layout.item_footer, null);
                    return new FooterViewHolder(view);
                case TYPE_ITEM:
                    View view2 = inflater.inflate(R.layout.app_item, null);
                    return new AppViewHolder(view2);


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


            /**
             * 删除某个数据项
             */
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrayList.remove(position);
                    notifyDataSetChanged();// 通知刷新适配器
                }
            });


            /**
             * 单击编辑按钮——响应事件为显示app简介
             */
            holder.ivUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, arrayList.get(position).getIntro(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size()+1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount()-1) {
                return TYPE_FOOTER;
            }
            return TYPE_ITEM;
        }
    }


}
