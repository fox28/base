package cn.ucai.day04_01;

import android.content.DialogInterface;
import android.preference.DialogPreference;
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
        builder.setTitle("俺是标准对话框的标题");
        builder.setMessage("额是标准对话框的Message");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "确认按钮被点击", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "取消按钮被点击", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("忽略", null);
        builder.show();
    }

    /**
     * 创建列表对话框
     * @param v
     */
    public void onListDialog(View v) {

    }

    /**
     * 创建自定义对话框
     * @param v
     */
    public void onCustomDialog(View v) {

    }
}
