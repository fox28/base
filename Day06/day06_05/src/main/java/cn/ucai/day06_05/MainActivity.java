package cn.ucai.day06_05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 解析app.xml文件
     * @param view
     */
    public void onClick(View view) {

        InputStream in =null;
        try {
            ArrayList<App> list = new ArrayList<>();
            App app = null;
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            in = getResources().openRawResource(R.raw.app);
            parser.setInput(in, "utf-8");
            for (int eventType = XmlPullParser.START_DOCUMENT; eventType != XmlPullParser.END_DOCUMENT;
                    eventType = parser.next()) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String tag = parser.getName(); // 获取标签名称
                        if (tag.equals("app")) {
                            // 解析属性：name， version， fileSize
                            String name = parser.getAttributeValue(null, "name");
                            String version = parser.getAttributeValue(null, "version");
                            int fileSize = Integer.parseInt(parser.getAttributeValue(null, "fileSize"));
                            app = new App(name, version, fileSize);

                        } else if ("thumb".equals(tag)) {
                            app.setThumb(parser.nextText());
                        } else if ("apk".equals(tag)) {
                            app.setApk(parser.nextText());
                        } else if ("intro".equals(tag)) {
                            app.setIntro(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String name = parser.getName();
                        if ("app".equals(name)) {
                            list.add(app);
                        }
                        break;
                }// end switch

            }// end for
            for (App app2 : list) {

                Log.i("main", app2.toString());
            }
            Toast.makeText(this, "输出内容到日志窗口", Toast.LENGTH_SHORT).show();
        } catch (XmlPullParserException e) {
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
