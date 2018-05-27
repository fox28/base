package cn.ucai.day03_07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText metUserName, metPassword, metConfirmPassword;
    RadioButton mrbMale;
    Spinner mspinCity;
    CheckBox mchkTour, mchkReading, mchkGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    private void initView() {
        metUserName = (EditText) findViewById(R.id.etUserName);
        metPassword = (EditText) findViewById(R.id.etPassword);
        metConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        mrbMale = (RadioButton) findViewById(R.id.rbFemale);
        mspinCity = (Spinner) findViewById(R.id.spinCity);
        mchkTour = (CheckBox) findViewById(R.id.chkTour);
        mchkReading = (CheckBox) findViewById(R.id.chkReading);
        mchkGame = (CheckBox) findViewById(R.id.chkGame);
    }

    public void register(View v) {
        String userName = metUserName.getText().toString();
        if (userName.length() == 0) {
            metUserName.requestFocus();
            metUserName.setError("账号不能为空");
            return;
        }

        String password = metPassword.getText().toString();
        if (password == null) {
            metPassword.requestFocus();
            metPassword.setError("密码不能为空");
            return;
        }

        String confirmPassword = metConfirmPassword.getText().toString();
        if(confirmPassword.isEmpty()){
            metConfirmPassword.requestFocus();
            metConfirmPassword.setError("确认密码不能为空");
            return;
        }
        if (! password.equals(confirmPassword)) {
            metPassword.requestFocus();
            metPassword.setError("两次输入密码不一致");
            metPassword.setText("");
            metConfirmPassword.setText("");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("账号：").append(userName).append(", 密码：").append(password);

        if (mrbMale.isChecked()) {
            sb.append(", 性别：男");
        } else {
            sb.append(", 性别：女");
        }

        sb.append(", 所在地：").append(mspinCity.getSelectedItem().toString());
        sb.append(", 爱好：");
        if (mchkTour.isChecked()) {
            sb.append("旅游，");
        }
        if (mchkReading.isChecked()) {
            sb.append("读书,");
        }
        if (mchkGame.isChecked()) {
            sb.append("游戏,");
        }

        sb.deleteCharAt(sb.length() - 1);

        Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
        Log.i("main", sb.toString());
    }

    public void cancel(View v) {
        metUserName.setText("");
        metPassword.setText("");
        metConfirmPassword.setText("");

    }
}
