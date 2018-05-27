package cn.ucai.day06_03;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
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
     * 外存储编程：
     * 将app.json保存到Android文件夹
     * @param view
     */
    public void saveToAndroid(View view) {
        FileOutputStream out=null;
        InputStream in = null;
        try {
            in = getAssets().open("app.json");
            File dir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(dir, "app.json");
            out = new FileOutputStream(file);

            byte[] byArr = new byte[1024 * 8];
            int len;
            while ((len = in.read(byArr)) != -1) {
                out.write(byArr, 0, len);
            }
            Toast.makeText(this, "已将app.json保存到Android文件夹", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (out != null)
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (in != null) {

                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 外存储编程
     * 从Android文件夹读取app.json文件
     * @param view
     */
    public void readFromAndroid(View view) {
        File dir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(dir, "app.json");
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            StringBuilder sb = new StringBuilder();
            byte[] byArr = new byte[1024 * 8];
            int len;
            while ((len = in.read(byArr)) != -1) {
                sb.append(new String(byArr, 0, len));
            }
            Log.i("main", sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null) {

                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
