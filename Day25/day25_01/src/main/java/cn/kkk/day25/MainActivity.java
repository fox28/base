package cn.kkk.day25;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    OnGetAvatarListener mOnGetAvatarListener;
    ImageView mivAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mivAvatar = (ImageView) findViewById(R.id.ivAvatar);
    }

    public void onClick(View view) {
        mOnGetAvatarListener = new OnGetAvatarListener(this, R.layout.activity_main);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        mOnGetAvatarListener.setAvatar(data, requestCode,mivAvatar);
    }
}
