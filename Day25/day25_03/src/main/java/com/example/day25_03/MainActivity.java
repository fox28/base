package com.example.day25_03;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.day25_03.fragment.BoutiqueFragment;
import com.example.day25_03.fragment.CategoryFragment;
import com.example.day25_03.fragment.CollectFragment;
import com.example.day25_03.fragment.MeFragment;
import com.example.day25_03.fragment.NewGoodsFragment;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.mi_new_goods:
                ft.replace(R.id.layout_content, new NewGoodsFragment()).commit();
                break;
            case R.id.mi_boutique:
                ft.replace(R.id.layout_content, new BoutiqueFragment()).commit();
                break;
            case R.id.mi_category:
                ft.replace(R.id.layout_content, new CategoryFragment()).commit();
                break;
            case R.id.mi_collect:
                ft.replace(R.id.layout_content, new CollectFragment()).commit();
                break;
            case R.id.mi_me:
                ft.replace(R.id.layout_content, new MeFragment()).commit();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
