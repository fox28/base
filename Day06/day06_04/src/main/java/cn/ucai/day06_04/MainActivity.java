package cn.ucai.day06_04;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    static final String FILE_NAME = "ucai_logo.png";
    ImageView mivUcaiLogo, mivUcaiLogo2, mivUcaiLogo3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intiView();
    }

    /**
     * 初始化
     */
    private void intiView() {
        mivUcaiLogo = (ImageView) findViewById(R.id.ivUcaiLogo);
        mivUcaiLogo2 = (ImageView) findViewById(R.id.ivUcaiLogo2);
        mivUcaiLogo3 = (ImageView) findViewById(R.id.ivUcaiLogo3);
    }

    /**
     * 将图片保存至SD卡
     * @param view
     */
    public void saveToSDCard(View view) {
        InputStream in = null;
        FileOutputStream out = null;

        try {
            in = getAssets().open(FILE_NAME);
            File dir = Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_PICTURES);
            File file = new File(dir, FILE_NAME);
            out = new FileOutputStream(file);
            byte[] byArr = new byte[1024 * 8];
            int len;
            while ((len = in.read(byArr)) != -1) {
                out.write(byArr, 0, len);
            }
            Toast.makeText(this, "图片已保存至SD卡", Toast.LENGTH_SHORT).show();

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
            try {
                if (out != null) {

                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从SD卡读取图片
     * @param view
     */
    public void readFromSDCard(View view) {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File file = new File(dir, FILE_NAME);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        mivUcaiLogo.setImageBitmap(bitmap);
    }

    /**
     * 保存图片至Android文件夹
     * @param view
     */
    public void saveToAndroid(View view) {
        InputStream in = null;
        FileOutputStream out = null;
        try {
            in = getAssets().open(FILE_NAME);
            File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File file = new File(dir, FILE_NAME);
            out = new FileOutputStream(file);

            byte[] byArr = new byte[1024 * 8];
            int len;
            while ((len = in.read()) != -1) {
                out.write(byArr, 0, len);
            }
            Toast.makeText(this, "图片已保存至Android文件夹", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从Android文件夹读取图片
     * @param view
     */
    public void readFromAndroid(View view) {
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(dir, FILE_NAME);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        mivUcaiLogo.setImageBitmap(bitmap);
        Toast.makeText(this, "从Android文件夹读取图片", Toast.LENGTH_SHORT).show();

    }


}
