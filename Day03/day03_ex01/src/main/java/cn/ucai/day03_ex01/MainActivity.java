package cn.ucai.day03_ex01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView mivContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mivContent = (ImageView) findViewById(R.id.imageView);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvNewGoods:
                mivContent.setImageResource(R.drawable.new_goods);
                break;
            case R.id.tvBoutique:
                mivContent.setImageResource(R.drawable.boutique);
                break;
            case R.id.tvCategory:
                mivContent.setImageResource(R.drawable.category);
                break;
            case R.id.tvCart:
                mivContent.setImageResource(R.drawable.cart);
                break;
            case R.id.tvCollect:
                mivContent.setImageResource(R.drawable.collect);
                break;
        }
    }
}
