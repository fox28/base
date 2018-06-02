package com.example.day25_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolBar);

        setSupportActionBar(mToolbar);
        mToolbar.setTitle("主标题");
        mToolbar.setSubtitle("副标题");
        mToolbar.setLogo(R.mipmap.ic_launcher);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_new_goods:
                Toast.makeText(this, "进入新品页面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mi_boutique:
                Toast.makeText(this, "进入精品页面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mi_category:
                Toast.makeText(this, "进入分类页面", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
