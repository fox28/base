package com.example.day24;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends AppCompatActivity {
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }*/


    MapView mMapView;
    /*BMapManager mBMapMan;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* mBMapMan=new BMapManager(getApplication());
        mBMapMan.init("com.baidu.lbsapi.API_KEY", null);*/
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.mapView);
    }

    public void onClick(View view) {
        //创建坐标点，包含纬度和经度
        LatLng latLng = new LatLng(39.9484051, 116.324706);
        //创建地图状态对象
        MapStatus mapStatus = new MapStatus.Builder().target(latLng).zoom(17).build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mMapView.getMap().setMapStatus(mapStatusUpdate);
    }


}
