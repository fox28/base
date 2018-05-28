package com.example.day17_05_02;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpireJobFragment extends Fragment {
    TextView mtvProperty,mtvExpireJob,mtvAddress,mtvSalary;

    Resume mResume;

    IExpireJob mActivity;

    public ExpireJobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_expire_job, container, false);
        initView(layout);
        return layout;
    }

    private void initView(View layout) {
        mtvAddress = (TextView) layout.findViewById(R.id.tvWorkAddress);
        mtvExpireJob = (TextView) layout.findViewById(R.id.tvExpireJob);
        mtvProperty = (TextView) layout.findViewById(R.id.tvProperty);
        mtvSalary = (TextView) layout.findViewById(R.id.tvSalary);
    }

    /**
     * Activity与ExpireJobFragment之间数据传递的窗口
     * 由MainActivity实现IExpireJob接口
     */
    public interface IExpireJob{
        Resume getResume();

        void setResume(Resume resume);
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity = (IExpireJob) getActivity();
        mResume= mActivity.getResume();
        showExpireJob(mResume);
    }

    private void showExpireJob(Resume resume) {
        mtvAddress.setText(resume.getAddress());
        mtvSalary.setText(resume.getSalary());
        mtvProperty.setText(resume.getProperty());
        mtvExpireJob.setText(resume.getExpireJob());
    }

}
