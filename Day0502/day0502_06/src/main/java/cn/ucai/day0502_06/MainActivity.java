package cn.ucai.day0502_06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {
    RatingBar mRatingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }

    /**
     * 创建监听接口
     */
    private void setListener() {
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.i("main", "rating=" + rating);
            }
        });
    }

    private void initView() {
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
    }
}
