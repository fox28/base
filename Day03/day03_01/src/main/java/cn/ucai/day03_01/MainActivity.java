package cn.ucai.day03_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置Activity显示和管理的视图(布局)
        setContentView(R.layout.activity_main);
    }

    /**
     * 响应注册按钮的单击事件
     * @param v 被点击的View，实际就是注册按钮
     */
    public void register(View v) {
        if (v instanceof Button) {
            Button btnRegister = (Button) v;
            Log.i("mai" + "n", btnRegister.getText().toString());
        }

    }

    public void login(View l) {
        Log.i("main", l.getTag().toString());
    }
}
