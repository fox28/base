package com.example.day10_07;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    static final String NAME = "name";
    static final String VERSION = "version";
    static final String FILE_SIZE = "flieSize";
    static final String INTRO = "intro";
    static final String PIC_ID = "picId";

    ArrayList<Map<String, Object>> mAppList;
    ListView mlvApp;
    SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView() {
        mlvApp = (ListView) findViewById(R.id.lvApp);
        mAdapter = new SimpleAdapter(this, mAppList, R.layout.app_item
                , new String[]{NAME, VERSION, FILE_SIZE, PIC_ID}
                , new int[]{  R.id.tvAppName, R.id.tvAppVersion, R.id.tvAppFileSize, R.id.ivThumb});
        mlvApp.setAdapter(mAdapter);

    }

    /**
     * 从res/values/arrays中获取相关数据，用于适配器的调取
     */
    private void initData() {
        int[] picId={
                R.drawable.tencent_safe, R.drawable.baidu_safe,R.drawable.kingsoft_safe,
                R.drawable.an_doctor,R.drawable.ruixing_safe,R.drawable.wangqin_safe,
                R.drawable.lost_safe,R.drawable.bigspider_safe,R.drawable.avg_safe,
                R.drawable.lbe_safe,R.drawable.mobile_an_safe
        };
        Resources res = getResources();
        String[] nameArr = res.getStringArray(R.array.names);
        String[] versionArr = res.getStringArray(R.array.version);
        int[] fileSizeArr = res.getIntArray(R.array.file_size);
        String[] introArr = res.getStringArray(R.array.intro);

        mAppList = new ArrayList<>();
        for (int i=0; i<picId.length; i++) {
            HashMap<String, Object> app = new HashMap<>();
            app.put(NAME, nameArr[i]);
            app.put(PIC_ID, picId[i]);
            app.put(VERSION, versionArr[i]);
            app.put(FILE_SIZE, fileSizeArr[i]+"k");
            app.put(INTRO, introArr[i]);
            mAppList.add(app);
        }



    }
}
