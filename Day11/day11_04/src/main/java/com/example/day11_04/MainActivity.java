package com.example.day11_04;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day11_04.bean.AppBean;
import com.example.day11_04.dao.AppDao;

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
        mrvApp = (RecyclerView) findViewById(R.id.rvApp);
    }

    private void initData() {
        mArrayList = AppDao.getData(this);

    }

    /**
     * 创建响应按钮的单击事件
     * @param view
     */
    public void onClick(View view) {
        StaggeredGridLayoutManager layoutManager = null;
        switch (view.getId()) {
            case R.id.btnGallery:
                layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
                break;
            case R.id.btnListView:
                layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                break;
            case R.id.btnGridViewH:
                layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
                break;
            case R.id.btnGridViewV:
                layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                break;

        }
        mAdapter = new AppAdapter(this, mArrayList);
        mrvApp.setAdapter(mAdapter);
        mrvApp.setLayoutManager(layoutManager);

    }


    /**
     * 将app_item2.xml解析为java对象
     */
    class AppViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        TextView tvName;
        public AppViewHolder(View itemView) {
            super(itemView);
            ivThumb = (ImageView) itemView.findViewById(R.id.ivThumb);
            tvName = (TextView) itemView.findViewById(R.id.tvName);

        }
    }

    /**
     * app适配器，负责将数据（集合mArrayList）显示在列表中
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
            View view = View.inflate(context, R.layout.app_item, null);
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
            return arrayList.size();
        }
    }


}
