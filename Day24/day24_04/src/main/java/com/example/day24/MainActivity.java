package com.example.day24;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends AppCompatActivity {
    MapView mMapView;
    LocationClient mLocationClient;
    boolean misFirst = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }

    private void setListener() {
        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                if (bdLocation == null || mMapView == null) {
                    return;
                }
                MyLocationData data = new MyLocationData.Builder()
                        .accuracy(bdLocation.getRadius())
                        .latitude(bdLocation.getLatitude())
                        .longitude(bdLocation.getLongitude())
                        .build();
                LatLng latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                mMapView.getMap().clear();
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
                MarkerOptions options = new MarkerOptions().position(latLng).icon(bitmapDescriptor);
                mMapView.getMap().addOverlay(options);

                if (misFirst) {
                    misFirst = false;
                    MapStatus mapStatus = new MapStatus.Builder().target(latLng).zoom(21).build();
                    MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
                    mMapView.getMap().setMapStatus(mapStatusUpdate);
                }


            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {

            }
        });
    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.mapView);

        mMapView.getMap().setMyLocationEnabled(true);
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd9LL");// 设置坐标系
        option.setOpenGps(true);
        option.setScanSpan(1000);

        mLocationClient = new LocationClient(this);
        mLocationClient.setLocOption(option);
    }

    public void onStart(View view) {
        mLocationClient.start();
    }

    public void onStop(View view) {
        mLocationClient.stop();
    }
}
