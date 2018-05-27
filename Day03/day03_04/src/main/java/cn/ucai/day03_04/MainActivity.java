package cn.ucai.day03_04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置单击事件监听
        MyOnClickListener listener = new MyOnClickListener();
        findViewById(R.id.btnLogin).setOnClickListener(listener);

        findViewById(R.id.btnRegister).setOnClickListener(listener);
    }






    /**
     * UI事件处理方式3：
     * 内部类处理单击事件
     */
    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnLogin:
                    Toast.makeText(MainActivity.this, "执行登录操作00", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnRegister:
                    Toast.makeText(MainActivity.this, "执行注册操作11", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
