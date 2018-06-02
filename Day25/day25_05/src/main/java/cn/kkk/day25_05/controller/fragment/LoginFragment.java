package cn.kkk.day25_05.controller.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import cn.kkk.day25_05.R;
import cn.kkk.day25_05.bean.UserBean;
import cn.kkk.day25_05.model.net.IModelLogin;
import cn.kkk.day25_05.model.net.ModelLogin;
import cn.kkk.day25_05.model.net.OnCompleteListener;
import cn.kkk.day25_05.model.utils.OkUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    IModelLogin mLogin;
    EditText metUserName, metPassword;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_login, container, false);
        mLogin = new ModelLogin();
        initView(layout);
        setListener(layout);

        return layout;
    }

    private void initView(View view) {
        metUserName = (EditText) view.findViewById(R.id.etUserName);
        metPassword = (EditText) view.findViewById(R.id.etPassword);
    }

    public void setListener(View view) {
        view.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = metUserName.getText().toString();
                String password = metPassword.getText().toString();
                mLogin.login(getActivity(),userName,password, new OnCompleteListener<UserBean>(){
                    @Override
                    public void onSuccess(UserBean result) {
                        Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
                        Log.i("main", result.toString());
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
