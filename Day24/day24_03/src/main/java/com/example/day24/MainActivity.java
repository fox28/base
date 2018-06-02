package com.example.day24;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends AppCompatActivity {
    EditText metZoom, metRotate, metOverlook;
    MapView mMapView;
    TextView mtvState;
    LatLng mLatLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }





    private void setListener() {
        setOnMapClickListener();
        setOnMapDoubleClickListener();

    }

    private void setOnMapDoubleClickListener() {
        mMapView.getMap().setOnMapDoubleClickListener(new BaiduMap.OnMapDoubleClickListener() {
            @Override
            public void onMapDoubleClick(LatLng latLng) {
                updateState("双击",latLng);
            }
        });
    }

    private void setOnMapClickListener() {
        mMapView.getMap().setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mtvState.setText("123");
                updateState("单击", latLng);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
    }

    private void updateState(String text, LatLng latLng) {
        text += String.format(", 纬度: %.3f, 经度: %.4f", latLng.latitude, latLng.longitude);
        mtvState.setText(text);
        /*text += String.format(",纬度:%.3f,经度:%.3f", latLng.latitude, latLng.longitude);
        mtvState.setText(text);*/

    }

    private void initView() {
        metZoom = (EditText) findViewById(R.id.etZoom);
        metRotate = (EditText) findViewById(R.id.etRotate);
        metOverlook = (EditText) findViewById(R.id.etOverlook);
        mtvState = (TextView) findViewById(R.id.tvState);

        mMapView = (MapView) findViewById(R.id.mapView);
        mLatLng = new LatLng(39.9484051, 116.324706);
        MapStatus mapStatus = new MapStatus.Builder().target(mLatLng).zoom(17).rotate(30).overlook(-30).build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mMapView.getMap().setMapStatus(mapStatusUpdate);
    }


    public void onZoom(View view) {
        int zoom = Integer.parseInt(metZoom.getText().toString());
        MapStatus mapStatus = new MapStatus.Builder().zoom(zoom).build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mMapView.getMap().setMapStatus(mapStatusUpdate);
    }

    public void onRotate(View view) {
        float rotate = Float.parseFloat(metRotate.getText().toString());
        MapStatus mapStatus = new MapStatus.Builder().rotate(rotate).build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mMapView.getMap().setMapStatus(mapStatusUpdate);
    }

    public void onOverlook(View view) {
        float overlook = Float.parseFloat(metOverlook.getText().toString());
        MapStatus mapStatus = new MapStatus.Builder().overlook(overlook).build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mMapView.getMap().setMapStatus(mapStatusUpdate);
    }
}
