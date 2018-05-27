package com.example.day10_05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView mlvApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mlvApp = (ListView) findViewById(R.id.lvApp);
        // 方法一：构造器创建ArrayAdapter
        /*String[] nameArr = getResources().getStringArray(R.array.apps);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_list_item_1, nameArr);*/

        // 方式2，用createFromResource方法创建adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.apps, android.R.layout.simple_list_item_1);
        mlvApp.setAdapter(adapter);
    }

}
