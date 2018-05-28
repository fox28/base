package com.example.day11_02;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day11_02.bean.AppBean;
import com.example.day11_02.dao.AppDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<AppBean> mArrayList;
    RecyclerView mrvApp;
    AppAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView() {
        mrvApp = (RecyclerView) findViewById(R.id.rvApp); // 在acitivity中实例化该按钮对象

        /**
         * 定义布局管理器对象
         * 在网格中显示
         * 4列
         */
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        mrvApp.setLayoutManager(layoutManager); // 设置布局管理器

        mAdapter = new AppAdapter(this, mArrayList); // 实例化适配器，适配器对象
        mrvApp.setAdapter(mAdapter); // 设置RecyclerView（这里指mrvApp）与适配器mAdapter关联




    }

    /**
     * 获取数据集合，即创建在适配器中显示的数据
     */
    private void initData() {
        mArrayList = AppDao.getData(this);

    }

    /**
     * 将app_item.xml解析为一个布局对象
     */
    class AppViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        TextView tvName;

        // 构造方法
        public AppViewHolder(View itemView) {
            super(itemView);
            ivThumb = (ImageView) itemView.findViewById(R.id.ivThumb);
            tvName = (TextView) itemView.findViewById(R.id.tvName);

        }
    }

    /**
     * app适配器，负责将数据显示在列表项中
     */
    class AppAdapter extends RecyclerView.Adapter<AppViewHolder> {
        Context context;
        ArrayList<AppBean> arrayList;

        public AppAdapter(Context context, ArrayList<AppBean> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        @Override
        public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = View.inflate(context, R.layout.app_item, null);
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.app_item, null);
            return new AppViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AppViewHolder holder, int position) {
            AppBean app = arrayList.get(position);
            holder.ivThumb.setImageResource(app.getPhotoId());
            holder.tvName.setText(app.getName());
        }

        @Override
        public int getItemCount() {
            return mArrayList.size();
        }
    }
}
