package com.example.day07_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {
    UserDao mUserDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserDao = new UserDao(this, 1);
    }



    /**
     * 删除记录
     * @param view
     */
    public void deleteRecorder(View view) {
        boolean isSuccess = mUserDao.deleteRecorder("菲");
        if (isSuccess) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "没有符合条件的记录", Toast.LENGTH_SHORT).show();
            showAllResult();
        }
    }

    /**
     * 修改记录
     * @param view
     */
    public void updateRecorder(View view) {
        User user = new User("张飞飞","1234561","12345678","ZhangFeiFei@126.com");
        boolean isUpdata = mUserDao.updateRecord(user);
        if (isUpdata) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 查询所有记录
     * @param view
     */
    public void queryAllRecorder(View view) {
        showAllResult();
    }

    private void showAllResult() {
        ArrayList<User> list = mUserDao.queryAll();
        for (User user : list) {
            Log.i("main", user.toString());
            Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 查询指定姓名的记录
     * @param view
     */
    public void queryRecorderByName(View view) {
//        mUserDao.
    }
}
