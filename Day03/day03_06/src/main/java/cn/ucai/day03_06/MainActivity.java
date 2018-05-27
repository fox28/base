package cn.ucai.day03_06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    MyOnClickListener mOnClickListener;
    EditText metUserName, metPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();

    }


    private void initView() {
        metUserName = (EditText) findViewById(R.id.etUserName);
        metPassword = (EditText) findViewById(R.id.etPassword);
    }

    private void setListener() {
        mOnClickListener = new MyOnClickListener(this, metUserName,metPassword);
        findViewById(R.id.btnExit).setOnClickListener(mOnClickListener);
        findViewById(R.id.btnLogin).setOnClickListener(mOnClickListener);
    }

}
