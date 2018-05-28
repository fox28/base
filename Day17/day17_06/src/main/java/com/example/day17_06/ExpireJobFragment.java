package com.example.day17_06;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.ucai.day17_06.Resume;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpireJobFragment extends Fragment {
    TextView mtvProperty,mtvExpireJob,mtvAddress,mtvSalary;

    Resume mResume;

    ExpireReceiver mReceiver;

    public ExpireJobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expire_job, container, false);

        initView(view);
        registerReceiver();

        return view;
    }


    private void registerReceiver() {
        mReceiver = new ExpireReceiver();
        IntentFilter filter = new IntentFilter("update_resume");
        getActivity().registerReceiver(mReceiver, filter);
    }

    private void initView(View layout) {
        mtvAddress = (TextView) layout.findViewById(R.id.tvWorkAddress);
        mtvExpireJob = (TextView) layout.findViewById(R.id.tvExpireJob);
        mtvProperty = (TextView) layout.findViewById(R.id.tvProperty);
        mtvSalary = (TextView) layout.findViewById(R.id.tvSalary);

    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();
        mResume = bundle.getParcelable("resume");
        showExpireJob(mResume);
    }

    class ExpireReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            mResume = intent.getParcelableExtra("resume");
            showExpireJob(mResume);
        }
    }

    private void showExpireJob(Resume resume) {
        mtvAddress.setText(resume.getWorkAddress());
        mtvExpireJob.setText(resume.getExpireJob());
        mtvProperty.setText(resume.getProperty());
        mtvSalary.setText(resume.getSalary());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mReceiver);
    }



}
