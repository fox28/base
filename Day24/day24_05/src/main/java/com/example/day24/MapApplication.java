package com.example.day24;

import android.app.Application;
import android.widget.EditText;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.search.geocode.GeoCoder;

/**
 * Created by apple on 2017/3/21.
 */

public class MapApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SDKInitializer.initialize(this);

    }
}
