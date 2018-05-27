package cn.ucai.day03_06;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by the_one on 2017/2/11.
 */

public class MyOnClickListener implements View.OnClickListener {
    MainActivity mActivity;
    EditText metUserName, metPassword;

    public MyOnClickListener(MainActivity mActivity,
                             EditText metUserName, EditText metPassword) {
        this.mActivity = mActivity;
        this.metUserName = metUserName;
        this.metPassword = metPassword;
    }

    @Override
    public void onClick(View v) {
        String userName = metUserName.getText().toString();
        String password = metPassword.getText().toString();
        switch (v.getId()){
            case R.id.btnExit:
                mActivity.finish();//退出Activity并关闭当前页面
                break;
            case  R.id.btnLogin:
                Toast.makeText(mActivity, "执行登录操作", Toast.LENGTH_LONG).show();
                Log.i("main", "账号:"+userName+", 密码:"+password);
        }

    }
}
