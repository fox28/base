package cn.ucai.day03_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * n能处理登录和注册的单击事件
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                Toast toast = Toast.makeText(this, "执行登录操作", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.btnRegister:
                Toast.makeText(this, "执行注册操作", Toast.LENGTH_LONG).show();
        }
    }
}
