package com.example.day17_03_02.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day17_03_02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlwaysContactFragment extends Fragment {


    public AlwaysContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_always_contact, container, false);
    }

}
