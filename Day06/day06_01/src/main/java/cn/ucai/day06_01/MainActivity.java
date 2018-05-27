package cn.ucai.day06_01;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText metId, metPassword;
    RadioButton mrbSaveAll, mrbSaveId, mrbNotSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        readLoginInfo();



    }

    private void readLoginInfo() {
        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
        int id = sp.getInt("id", 0);
        if (id > 0) {
            metId.setText(id+"");
        }

        String password = sp.getString("password", "");
        if (!password.isEmpty()) {
            metPassword.setText(password);

        }
    }

    /***
     * 初始化各个view的值
     */
    private void initView() {
        metId = (EditText) findViewById(R.id.etId);
        metPassword = (EditText) findViewById(R.id.etPassword);
        mrbSaveAll = (RadioButton) findViewById(R.id.rbSaveAll);
        mrbSaveId = (RadioButton) findViewById(R.id.rbSaveId);
        mrbNotSave = (RadioButton) findViewById(R.id.rbNotSave);
    }

    public void login(View view) {
        String strId  = metId.getText().toString();
        int id = Integer.parseInt(strId);
        String password =metPassword.getText().toString();

        SharedPreferences sp =getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (mrbSaveAll.isChecked()) {
            editor.putInt("id",id);
            editor.putString("password", password);
        } else if (mrbSaveId.isChecked()) {
            editor.clear();
            editor.putInt("id", id);

        } else {
            editor.clear();
        }
        editor.commit();

        Toast.makeText(this, "登录按钮被选定", Toast.LENGTH_SHORT).show();





    }


}
