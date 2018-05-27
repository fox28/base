package com.example.day10_08.adapter;

/**
 * Created by apple on 2017/2/26.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day10_08.R;
import com.example.day10_08.bean.AppBean;

import java.util.ArrayList;

/**
 * app适配器，负责将数据显示在列表中
 */
public class AppAdapter extends BaseAdapter{
    Context context;
    ArrayList<AppBean> arrayList;

    public AppAdapter(Context context, ArrayList<AppBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public AppBean getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       ViewHolder viewHolder = null;
        if (convertView == null) {
            // 创建第一屏的的列表项
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.app_item, null);
            viewHolder.ivThumb = (ImageView) convertView.findViewById(R.id.ivThumb);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvAppName);
            viewHolder.tvVersion = (TextView) convertView.findViewById(R.id.tvAppVersion);
            viewHolder.tvFileSize = (TextView) convertView.findViewById(R.id.tvAppFileSize);
            convertView.setTag(viewHolder);

        } else {
            // 从第二屏开始
            viewHolder= (ViewHolder) convertView.getTag();

        }
        //将app_item.xml转换为一个Java对象
        AppBean app = getItem(position);
        viewHolder.ivThumb.setImageResource(app.getPicId());
        viewHolder.tvName.setText(app.getName());
        viewHolder.tvVersion.setText(app.getVersion());
        viewHolder.tvFileSize.setText(app.getFileSize()+"kb");

        return convertView;





     }








    // 创建一个成员内部类ViewHolder， 一般方法可以直接使用成员内部类，通过对象. 调用其中的属性和方法
    class ViewHolder{
        ImageView ivThumb;
        TextView tvName;
        TextView tvVersion;
        TextView tvFileSize;
    }
}
