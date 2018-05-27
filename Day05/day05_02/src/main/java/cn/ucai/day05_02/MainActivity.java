package cn.ucai.day05_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RatingBar mRatingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initview();
        setListener();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.i("main", "ratng=" + rating);
                Toast.makeText(MainActivity.this, "rating="+rating, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化mRatingBar的值
     */
    private void initview() {
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
    }
}
