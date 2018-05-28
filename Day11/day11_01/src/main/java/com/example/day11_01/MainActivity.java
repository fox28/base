package com.example.day11_01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day11_01.bean.AppBean;
import com.example.day11_01.dao.AppDao;

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

    /**
     * 实例化rvApp按钮
     * 待适配器定义好后，响应单击事件
     */
    private void initView() {
        mrvApp = (RecyclerView) findViewById(R.id.rvApp);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mrvApp.setLayoutManager(layoutManager);

        mAdapter = new AppAdapter(this, mArrayList);
        mrvApp.setAdapter(mAdapter);






    }

    /**
     * 获得数据集合，用于放入适配器中
     */
    private void initData() {
        mArrayList = AppDao.getData(this);

    }

    /**
     * 定义成员内部类，一般方法通过 对象. 调用其中的属性的方法
     * 定义一个ViewHolder
     */
    class AppViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        TextView tvName;

        public AppViewHolder(View itemView) {
            super(itemView);
            ivThumb = (ImageView) itemView.findViewById(R.id.ivThumb);// 通过查找id， 创建对象
            tvName = (TextView) itemView.findViewById(R.id.tvName);

        }
    }

    /**
     * 定义适配器，负责将数据显示在列表中
     */
    class AppAdapter extends RecyclerView.Adapter<AppViewHolder>{
        Context context;
        ArrayList<AppBean> arrayList;
        int positon = 0;


        // 定义全参构造方法
        public AppAdapter(Context context, ArrayList<AppBean> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        @Override
        public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(context, R.layout.app_item, null);
            Log.i("main","onCreateViewHolder, position = "+positon);
           /* LayoutInflater inflater = LayoutInflater.from(context);
            View view2 = inflater.inflate(R.layout.app_item, null);*/


            return new AppViewHolder(view); // 调用有参构造方法，通过view查找id
        }

        @Override
        public void onBindViewHolder(AppViewHolder holder, int position) {
            AppBean app = mArrayList.get(position);
            holder.ivThumb.setImageResource(app.getPhotoId());
            holder.tvName.setText(app.getName());
            this.positon = position;
        }

        @Override
        public int getItemCount() {
            return mArrayList.size();
        }
    }
}
