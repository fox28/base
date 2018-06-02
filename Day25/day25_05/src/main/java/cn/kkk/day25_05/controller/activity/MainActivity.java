package cn.kkk.day25_05.controller.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.kkk.day25_05.R;
import cn.kkk.day25_05.controller.fragment.DownloadAvatarFragment;
import cn.kkk.day25_05.controller.fragment.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.btn_login:
                ft.replace(R.id.layout_contact, new LoginFragment()).commit();
                break;
            case R.id.btn_downloadAvatar:
                ft.replace(R.id.layout_contact, new DownloadAvatarFragment()).commit();
                break;
            case R.id.btn_contact:

                break;
        }
    }

}
