package com.example.day10_02;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.day10_02.adapter.AppAdapter;
import com.example.day10_02.bean.AppBean;

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
        setListener();
    }

    /**
     * 设置监听事件
     */
    private void setListener() {
        setItemClickListener();
        setItemLongClickListener();

        
    }
    private void setItemClickListener() {
        mlvApp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppBean app = (AppBean) parent.getItemAtPosition(position);
                Log.i("main", app.getName()+"被单击"+app.toString());
            }
        });
    }

   

    private void setItemLongClickListener() {
        mlvApp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "列表被长按", Toast.LENGTH_SHORT).show();
                AppBean app = (AppBean) parent.getItemAtPosition(position);
                Log.i("main", app.getName()+"列表被长按");
                return true;
            }
        });
    }



    // 3.在Activity中实例化listview
    private void initView() {
        mlvApp = (ListView) findViewById(R.id.lvApp);
        mAdapter = new AppAdapter(this, mAppList);
        mlvApp.setAdapter(mAdapter);

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
         public AppBean(int picId, String name, String version, int fileSize)
     */
    private void initData() {


        int[] pictureIdArr = new int[]{
                R.drawable.tencent_safe, R.drawable.baidu_safe, R.drawable.kingsoft_safe,
                R.drawable.an_doctor, R.drawable.ruixing_safe, R.drawable.wangqin_safe,
                R.drawable.lost_safe,R.drawable.bigspider_safe,R.drawable.avg_safe,
                R.drawable.lbe_safe,R.drawable.mobile_an_safe
        };
        Resources resources = getResources();
        String[] nameArr = resources.getStringArray(R.array.name);
        String[] versionArr = resources.getStringArray(R.array.version);
        String[] fileSizeArr = resources.getStringArray(R.array.file_size);

        mAppList = new ArrayList<>();
        for (int i=0; i<pictureIdArr.length; i++) {
            AppBean app = new AppBean(pictureIdArr[i], nameArr[i], versionArr[i], fileSizeArr[i]);
            mAppList.add(app);
        }


    }
}
