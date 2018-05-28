package com.example.day14_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.day14_02.bean.AppBean;
import com.example.day14_02.utils.OkUtils;

public class MainActivity extends AppCompatActivity {
    AppBean[] mAppArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            mAppArr = (AppBean[]) savedInstanceState.getSerializable("appArr");
            printAppArr(mAppArr);
        } else {
            loadAppArr();
        }
    }

    private void loadAppArr() {
        OkUtils<AppBean[]> utils = new OkUtils<>(this);
        utils.url("http://10.0.2.2/app.json")
                .targetClass(AppBean[].class)
                .execute(new OkUtils.OnCompleteListener<AppBean[]>() {
                    @Override
                    public void onSuccess(AppBean[] result) {
                        mAppArr = result;
                        printAppArr(mAppArr);

                    }

                    @Override
                    public void onError(String error) {

                    }
                });
    }


    private void printAppArr(AppBean[] appArr) {
        for (AppBean app : appArr) {
            Log.i("main", app.toString());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("appArr", mAppArr);
    }
}
