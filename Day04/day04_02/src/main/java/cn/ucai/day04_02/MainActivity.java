package cn.ucai.day04_02;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
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
     * 创建标准对话框
     * @param v
     */
    public void onStandardDialog(View v) {
        // 创建对话框的构建者
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.setTitle("我是标准对话框")
                .setMessage("标准对话框的message")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "确认按钮被点击", Toast.LENGTH_SHORT).show();
                    }
                }).setNeutralButton("忽略", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "忽略按钮被点击", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "取消按钮被点击", Toast.LENGTH_SHORT).show();
                    }
                }).create();
        dialog.show();

    }

    /**
     * 创建列表对话框
     * @param v
     */
    public void onListDialog(View v) {
        Resources res = getResources();
        final String[] course = res.getStringArray(R.array.course);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择科目")
                .setItems(course, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, course[which]+"被选择", Toast.LENGTH_SHORT).show();
                    }
                }).create().show();

    }

    /**
     * 创建自定义对话框
     * @param v
     */
    public void onCustomDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.login_exit32x32)
                .setTitle("退出")
                .setView(R.layout.custom_layout)
                .setPositiveButton("走", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("不走", null)
                .create().show();


    }

}
