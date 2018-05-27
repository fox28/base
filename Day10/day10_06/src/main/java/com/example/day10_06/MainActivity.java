package com.example.day10_06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView mActv;
    MultiAutoCompleteTextView mMactv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /**
     * 通过activity_main.xml布局中查找id，创建按钮对象
     * 或者：在Activity中实例化相关控件控件，
     */
    private void initView() {
        mActv = (AutoCompleteTextView) findViewById(R.id.actv);
        String[] hintArr = getResources().getStringArray(R.array.hints);
        ArrayAdapter<CharSequence> adapterActv = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, hintArr);
        mActv.setAdapter(adapterActv);

        mMactv = (MultiAutoCompleteTextView) findViewById(R.id.mactv);
        ArrayAdapter<CharSequence> adapterMactv = ArrayAdapter.createFromResource(this, R.array.hints, android.R.layout.simple_list_item_1);
        mMactv.setAdapter(adapterMactv);
        mMactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
