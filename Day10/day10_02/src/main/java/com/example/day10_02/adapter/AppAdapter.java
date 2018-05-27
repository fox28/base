package com.example.day10_02.adapter;

/**
 * Created by apple on 2017/2/25.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day10_02.R;
import com.example.day10_02.bean.AppBean;

import java.util.ArrayList;

/**
 * app适配器，负责将数据显示在列表中
 */
public class AppAdapter extends BaseAdapter{
    Context context;
    ArrayList<AppBean> appList;

    public AppAdapter(Context context, ArrayList<AppBean> appList) {
        this.context = context;
        this.appList = appList;
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public AppBean getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 将res/layout/app.item解析为一个view对象
        View layout = View.inflate(context, R.layout.app_item, null);
        ImageView ivThumb = (ImageView) layout.findViewById(R.id.ivThumb);
        TextView tvName = (TextView) layout.findViewById(R.id.tvAppName);
        TextView tvVersion = (TextView) layout.findViewById(R.id.tvAppVersion);
        TextView tvFileSize = (TextView) layout.findViewById(R.id.tvAppFileSize);

        AppBean app = getItem(position);
        ivThumb.setImageResource(app.getPicId());
        tvName.setText(app.getName());
        tvVersion.setText("版本："+app.getVersion());
        tvFileSize.setText(app.getFileSize());

        return layout;
    }
}
