package com.example.day10_04.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day10_04.R;
import com.example.day10_04.bean.AppBean;

import java.util.ArrayList;


/**
 * Created by yao on 2017/2/16.
 */

public class AppAdapter extends BaseAdapter {
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = View.inflate(context, R.layout.app_item, null);
        ImageView ivThumb = (ImageView) layout.findViewById(R.id.ivThumb);
        TextView tvName = (TextView) layout.findViewById(R.id.tvAppName);

        AppBean app = getItem(position);
        ivThumb.setImageResource(app.getPicId());
        tvName.setText(app.getName());
        return layout;
    }
}
