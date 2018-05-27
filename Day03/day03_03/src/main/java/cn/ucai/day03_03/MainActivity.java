package cn.ucai.day03_03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button mbtnLogin;
    Button mbtnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtnLogin = (Button) findViewById(R.id.btnLogin);
        mbtnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "执行登录操作", Toast.LENGTH_SHORT).show();

            }
        });

        mbtnRegister = (Button) findViewById(R.id.btnRegister);
        mbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "执行注册操作", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
