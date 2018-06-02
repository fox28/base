package com.example.day24;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends AppCompatActivity {
    MapView mMapView;
    BitmapDescriptor mBd;

    LatLng mLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.mapView);

        mLatLng = new LatLng(39.9484051, 116.324706);
        MapStatus mapStatus = new MapStatus.Builder().target(mLatLng).zoom(19).build();
        MapStatusUpdate mapUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mMapView.getMap().setMapStatus(mapUpdate);

        initOverlay();
    }

    private void initOverlay() {
        mBd = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
        MarkerOptions markerOptions = new MarkerOptions().position(mLatLng).icon(mBd);
        mMapView.getMap().addOverlay(markerOptions);
    }

    public void onClear(View view) {
        mMapView.getMap().clear();// 清除所有覆盖物
    }

    public void onReset(View view) {
        initOverlay();

    }
}
