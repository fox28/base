package com.example.day17_03;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.day17_03.fragment.AlwaysContactFragment;
import com.example.day17_03.fragment.MyContactFragment;
import com.example.day17_03.fragment.StrangerFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendListener();
    }

    private void sendListener() {
        findViewById(R.id.btnAlwaysContact).setOnClickListener(this);
        findViewById(R.id.btnMyContact).setOnClickListener(this);
        findViewById(R.id.btnStranger).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.btnAlwaysContact:
                ft.replace(R.id.layout_content,new AlwaysContactFragment()).commit();
                break;
            case R.id.btnMyContact:
                ft.replace(R.id.layout_content, new MyContactFragment()).commit();
                break;
            case R.id.btnStranger:
                ft.replace(R.id.layout_content, new StrangerFragment()).commit();
                break;
        }


    }
}
