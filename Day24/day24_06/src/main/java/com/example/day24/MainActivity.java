package com.example.day24;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

public class MainActivity extends AppCompatActivity {
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }*/


    EditText metCity,metAddress,metResultPosition;
    EditText metLatitude,metLongtitude,metResultAddress;

    MapView mMapView;
    GeoCoder mGeoCoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGeoCoder=GeoCoder.newInstance();
        initView();
        setListener();
    }

    private void setListener() {
        mGeoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {
                if (result == null || result.error != GeoCodeResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(MainActivity.this, "未查询到结果", Toast.LENGTH_SHORT).show();
                    return;
                }
                mMapView.getMap().clear();
                BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
                MarkerOptions option = new MarkerOptions().position(result.getLocation()).icon(bd);
                mMapView.getMap().addOverlay(option);

                MapStatus mapStatus = new MapStatus.Builder().target(result.getLocation()).zoom(19).build();
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
                mMapView.getMap().setMapStatus(mapStatusUpdate);

                String text = String.format("纬度:%.3f,经度:%.3f", result.getLocation().latitude, result.getLocation().longitude);
                metResultPosition.setText(text);
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != GeoCodeResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(MainActivity.this, "未查询到结果", Toast.LENGTH_SHORT).show();
                    return;
                }
                mMapView.getMap().clear();
                BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
                MarkerOptions option = new MarkerOptions().position(result.getLocation()).icon(bd);
                mMapView.getMap().addOverlay(option);

                MapStatus mapStatus = new MapStatus.Builder().target(result.getLocation()).zoom(19).build();
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
                mMapView.getMap().setMapStatus(mapStatusUpdate);

                ReverseGeoCodeResult.AddressComponent detail = result.getAddressDetail();
                String details=detail.city+","+detail.district+","+detail.street+","+detail.streetNumber;
                metResultAddress.setText(details);
            }
        });
    }

    private void initView() {
        metResultPosition = (EditText) findViewById(R.id.etPosition);
        metCity = (EditText) findViewById(R.id.etCity);
        metAddress = (EditText) findViewById(R.id.etAddress);

        metLatitude = (EditText) findViewById(R.id.etLatitude);
        metLongtitude = (EditText) findViewById(R.id.etLongitude);
        metResultAddress = (EditText) findViewById(R.id.etResultAddress);

        mMapView = (MapView) findViewById(R.id.mapview);
    }

    public void onGeocode(View view) {
        String city=metCity.getText().toString();
        String address=metAddress.getText().toString();
        mGeoCoder.geocode(new GeoCodeOption().city(city).address(address));
    }

    public void onReverseGeocode(View view) {
        double latitude = Double.parseDouble(metLatitude.getText().toString());
        double longitude = Double.parseDouble(metLongtitude.getText().toString());
        LatLng latLng = new LatLng(latitude, longitude);
        mGeoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
    }



}
