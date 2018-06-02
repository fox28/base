package cn.kkk.day25_05.controller.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import cn.kkk.day25_05.R;
import cn.kkk.day25_05.model.net.IModelShowAvatar;
import cn.kkk.day25_05.model.net.ModelShowAvatar;
import cn.kkk.day25_05.model.net.OnCompleteListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadAvatarFragment extends Fragment {
    EditText metUserName;
    ImageView mivAvatar;
    IModelShowAvatar mShowAvatar;
    public DownloadAvatarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_download_avatar, container, false);
        mShowAvatar = new ModelShowAvatar();
        initView(layout);
        setListener(layout);
        return layout;
    }

    private void setListener(View layout) {
        layout.findViewById(R.id.btn_downloadAvatar_avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = metUserName.getText().toString();
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                final File file = new File(dir, userName + ".jpg");
                mShowAvatar.downloadAvatar(getActivity(),userName,file, new OnCompleteListener<Message>(){
                    @Override
                    public void onSuccess(Message result) {
                        Toast.makeText(getActivity(), "下载成功", Toast.LENGTH_SHORT).show();
                        Log.i("main", result.toString());
                        mivAvatar.setImageURI(Uri.fromFile(file));
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initView(View view) {
        metUserName = (EditText) view.findViewById(R.id.etUserNameAvatar);
        mivAvatar = (ImageView) view.findViewById(R.id.ivAvatar);
    }

}
