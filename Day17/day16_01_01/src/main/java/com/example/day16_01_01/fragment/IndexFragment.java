package com.example.day16_01_01.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day16_01_01.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexFragment extends Fragment {


    public IndexFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("main", "indexFragment的方法onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("main", "indexFragment的方法onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("main", "indexFragment的方法onCreateView");
        return inflater.inflate(R.layout.fragment_index, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("main", "indexFragment的方法onActivityCreated");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("main", "indexFragment的方法onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("main", "indexFragment的方法onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("main", "indexFragment的方法onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("main", "indexFragment的方法onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("main", "indexFragment的方法onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("main", "indexFragment的方法onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("main", "indexFragment的方法onDetach");
    }
}
