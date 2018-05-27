package com.example.day10_08;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.day10_08.adapter.AppAdapter;
import com.example.day10_08.bean.AppBean;

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

    private void setListener() {
        setItemClickListener();
        setItemLongClickListener();
    }

    private void setItemLongClickListener() {
        mlvApp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "列表项被长按", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void setItemClickListener() {
        mlvApp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppBean app = (AppBean) parent.getItemAtPosition(position);
                Log.i("main", app.toString());
            }
        });
    }


    private void initData() {
        mAppList = new ArrayList<>();
        int[] picId={
                R.drawable.tencent_safe, R.drawable.baidu_safe,R.drawable.kingsoft_safe,
                R.drawable.an_doctor,R.drawable.ruixing_safe,R.drawable.wangqin_safe,
                R.drawable.lost_safe,R.drawable.bigspider_safe,R.drawable.avg_safe,
                R.drawable.lbe_safe,R.drawable.mobile_an_safe
        };
        Resources res = getResources();
        String[] names = res.getStringArray(R.array.names);
        String[] versions = res.getStringArray(R.array.version);
        int[] fileSizes = res.getIntArray(R.array.file_size);
        for(int i=0;i<names.length;i++) {
            AppBean app=new AppBean(picId[i],names[i],versions[i],fileSizes[i]);
            mAppList.add(app);
        }
    }

    private void initView() {
        mlvApp = (ListView) findViewById(R.id.lvApp);
        mAdapter = new AppAdapter(this, mAppList);
        mlvApp.setAdapter(mAdapter);
    }

}
