package com.example.day21_04.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day21_04.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends Fragment {


    public CollectFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("main", "CollectFragment.onCreate()");

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("main", "CollectFragment.onDestroy()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collect, container, false);
    }

}
