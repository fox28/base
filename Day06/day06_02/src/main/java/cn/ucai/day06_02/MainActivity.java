package cn.ucai.day06_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 将app.xml文件保存到内存卡，然后打印到日志窗口中
     * @param view
     */
    public void writeData(View view) {
        InputStream in = getResources().openRawResource(R.raw.app);
        try {
            FileOutputStream out = openFileOutput("app.xml", MODE_PRIVATE);
            byte[] byArr = new byte[1024 * 8];
            int len;//每次存取的字节数
            while ((len = in.read(byArr)) != -1) {
                out.write(byArr, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "文件已保存至内存卡", Toast.LENGTH_SHORT).show();



    }


    /**
     * 从内存卡读取app.xml文件，并写入日志窗口
     * @param view
     */
    public void readData(View view) {
        try {
            FileInputStream in = openFileInput("app.xml");
            byte[] byArr = new byte[1024*8];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = in.read(byArr)) != -1) {
                sb.append(new String(byArr, 0, len));
            }
            Log.i("main", sb.toString());
            Toast.makeText(this, "文件已经写入日志窗口", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    /*public void readData(View view) {
        try {
            FileInputStream in = openFileInput("app.xml");
            byte[] buffer = new byte[1024 * 8];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = in.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, len));
            }
            Log.i("main", sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


}
