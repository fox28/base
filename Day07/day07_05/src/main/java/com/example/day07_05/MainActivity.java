package com.example.day07_05;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText metPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        metPhone = (EditText) findViewById(R.id.etPhone);

    }

    /**
     * 开始查询电话号码
     * @param view
     */
    public void lookUp(View view) {
        // 1.获取编辑框中国输入的查询号码
        String phone = metPhone.getText().toString();
        // 2.获取ContentResolve实例
        ContentResolver resolver = getContentResolver();
        // 3.设置查询的Uri
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, phone);
        // 4.查询
        Cursor c = resolver.query(uri, null, null, null, null);
        // 5.获取查询结果
        if (c.moveToNext()) {
            String name = c.getString(c.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
            Log.i("main", phone +"名字是："+name);
            Toast.makeText(this, phone + "的名字是" + name, Toast.LENGTH_LONG).show();
        }


    }
}
