package com.example.ex06_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
     * 用代码解析xml文件
     * 文件保存在项目的assets文件夹中，文件名：users.xml
     * @param view
     */
    public void onParseUsers(View view) {
        ArrayList<UserBean> list = new ArrayList<>();
        UserBean user = null;
        try {
            InputStream in = getAssets().open("users.xml");
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "utf-8");
            for(int eventType=XmlPullParser.START_DOCUMENT; eventType != XmlPullParser.END_DOCUMENT;
                    eventType = parser.next()) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();// 获得标签名字
                        if ("user".equals(name)) {
                            user = new UserBean();
                            user.setId(Integer.parseInt(parser.getAttributeValue(null, "id")));
                        } else if ("name".equals(name)) {
                            user.setName(parser.nextText());
                        } else if ("password".equals(name)) {
                            user.setPassword(parser.nextText());
                        } else if ("phone".equals(name)) {
                            user.setPhone(parser.nextText());
                        } else if ("email".equals(name)) {
                            user.setEmail(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("user".equals(parser.getName())) {
                            list.add(user);
                        }
                        break;

                }//end switch
            }// end for
            for (UserBean u : list) {
                Log.i("main", u.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
