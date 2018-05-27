package com.example.day10_03;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.day10_03.bean.AppBean;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView mlvApp;
    ArrayList<AppBean> mAppList;
    AppAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    /*
     <item>腾讯手机管家</item>
        <item>百度手机卫士</item>
        <item>金山毒霸</item>
        <item>安医生</item>
        <item>瑞星手机安全软件</item>
        <item>网秦安全</item>
        <item>防盗卫士</item>
        <item>大蜘蛛反病毒</item>
        <item>AVG</item>
        <item>LBE安全大师</item>
        <item>摩安卫士</item>
     */
    private void initData() {
        int[] picIdArr = new int[]{
                R.drawable.tencent_safe, R.drawable.baidu_safe, R.drawable.kingsoft_safe,
                R.drawable.an_doctor, R.drawable.ruixing_safe, R.drawable.wangqin_safe,
                R.drawable.lost_safe,R.drawable.bigspider_safe,R.drawable.avg_safe,
                R.drawable.lbe_safe,R.drawable.mobile_an_safe
        };
        Resources res = getResources();
        // 从res/values/arrays中获取相关数据
        String[] nameArr = res.getStringArray(R.array.name);
        String[] versionArr = res.getStringArray(R.array.verson);
        int[] fileSizeArr = res.getIntArray(R.array.file_size);
        String[] introArr = res.getStringArray(R.array.intro);

        mAppList = new ArrayList<>();
        for(int i=0; i<nameArr.length; i++) {
            AppBean app = new AppBean(picIdArr[i], nameArr[i], versionArr[i], fileSizeArr[i], introArr[i]);
            mAppList.add(app);
        }


    }

    /**
     * 在Activity中实例化ListView控件，
     * 通过activity_main.xml布局中查找id，创建按钮对象
     */
    private void initView() {
        mlvApp = (ListView) findViewById(R.id.lvApp);
        mAdapter = new AppAdapter();
        mlvApp.setAdapter(mAdapter);
    }

    /**
     * app适配器，负责将数据显示在列表中
     */
    class AppAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public AppBean getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 将res/layout/app.item解析为一个布局对象
            View view = View.inflate(MainActivity.this, R.layout.app_item, null);
            ImageView ivLogo = (ImageView) view.findViewById(R.id.ivThumb);
            TextView tvName = (TextView) view.findViewById(R.id.tvAppName);
            TextView tvVersion = (TextView) view.findViewById(R.id.tvAppVersion);
            TextView tvFileSize = (TextView) view.findViewById(R.id.tvAppFileSize);

            AppBean app = getItem(position);
//            AppBean app = mAppList.get(position);

            ivLogo.setImageResource(app.getPicId());
            tvName.setText(app.getName());
            tvVersion.setText("版本："+app.getVersion());
            tvFileSize.setText(app.getFileSize()+"k");

            return view;
        }
    }

}




