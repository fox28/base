package cn.ucai.day03_05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.btnRegister).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                Toast.makeText(MainActivity.this, "执行登录操作05", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRegister:
                Toast.makeText(MainActivity.this, "执行注册操作05", Toast.LENGTH_SHORT).show();
                break;
        }
    }



}
