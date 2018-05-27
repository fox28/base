package com.example.day10_04;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.day10_04.adapter.AppAdapter;
import com.example.day10_04.bean.AppBean;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<AppBean> mArrayList;
    GridView mgvApp;
    AppAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        //设置监听事件
        setListener();

    }

    private void setListener() {
        setItemClickListener();
        setItemLongClickListener();
    }

    private void setItemLongClickListener() {
        mgvApp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AppBean app = (AppBean) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "长按"+app.getName()+":"+app.getIntro(),
                        Toast.LENGTH_SHORT).show();

                return true;
            }
        });
    }

    private void setItemClickListener() {
        mgvApp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppBean app = (AppBean) parent.getItemAtPosition(position);
                Log.i("main", "单击"+app.getName() + ":" + app.getIntro());

            }
        });
    }


    private void initView() {
        mgvApp = (GridView) findViewById(R.id.gvApp);
        mAdapter = new AppAdapter(this, mArrayList);
        mgvApp.setAdapter(mAdapter);

    }

    private void initData() {
        mArrayList = new ArrayList<>();
//  public AppBean(int picId, String name, String version, int fileSize, String intro)
        int[] picArr = {
                R.drawable.tencent_safe, R.drawable.baidu_safe, R.drawable.kingsoft_safe,
                R.drawable.an_doctor, R.drawable.ruixing_safe, R.drawable.wangqin_safe,
                R.drawable.lost_safe,R.drawable.bigspider_safe,R.drawable.avg_safe,
                R.drawable.lbe_safe,R.drawable.mobile_an_safe
        };

        Resources res = getResources();
        String[] nameArr = res.getStringArray(R.array.names);
        String[] versionArr = res.getStringArray(R.array.version);
        int[] fileSizeArr = res.getIntArray(R.array.file_size);
        String[] introArr = res.getStringArray(R.array.intro);

        for (int i=0; i<picArr.length; i++) {
            AppBean app = new AppBean(picArr[i], nameArr[i], versionArr[i], fileSizeArr[i], introArr[i]);
            mArrayList.add(app);
        }

    }
}
