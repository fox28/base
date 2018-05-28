package com.example.day17_03_02;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.day17_03_02.fragment.AlwaysContactFragment;
import com.example.day17_03_02.fragment.MyContactFragment;
import com.example.day17_03_02.fragment.StrangerFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Fragment[] mFragmentArr;
    FragmentManager mManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListener();
        initFragment();
    }

    private void initFragment() {
        mFragmentArr = new Fragment[3];
        mFragmentArr[0] = new AlwaysContactFragment();
        mFragmentArr[1] = new MyContactFragment();
        mFragmentArr[2] = new StrangerFragment();


        mManager = getSupportFragmentManager();
        FragmentTransaction ft = mManager.beginTransaction();
        for (int i = 0; i < mFragmentArr.length; i++) {
            ft.add(R.id.layout_content, mFragmentArr[i]);
        }
        ft.commit();
        hideFragment();

    }

    private void hideFragment() {
        FragmentTransaction ft = mManager.beginTransaction();
        for (int i = 0; i < mFragmentArr.length; i++) {
            ft.hide(mFragmentArr[i]);
        }
        ft.commit();

    }

    private void setListener() {
        findViewById(R.id.btnAlwaysContact).setOnClickListener(this);
        findViewById(R.id.btnMyContact).setOnClickListener(this);
        findViewById(R.id.btnStranger).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        hideFragment();
        FragmentTransaction ft = mManager.beginTransaction();
        switch (v.getId()) {
            case R.id.btnAlwaysContact:
                ft.show(mFragmentArr[0]).commit();
                break;
            case R.id.btnMyContact:
                ft.show(mFragmentArr[1]).commit();
                break;
            case R.id.btnStranger:
                ft.show(mFragmentArr[2]).commit();
                break;
        }


    }
}
