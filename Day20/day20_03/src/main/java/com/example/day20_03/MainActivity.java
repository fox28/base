package com.example.day20_03;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.textView);
    }

    public void onClick(View view) {
        Typeface typeface = null;
        switch (view.getId()) {
            case R.id.btnDefaultBold:
                typeface = Typeface.DEFAULT_BOLD;
                break;
            case R.id.btnFzcy:
                typeface = Typeface.createFromAsset(getAssets(), "fzcy.ttf");
                break;
            case R.id.btnFzgl:
                typeface = Typeface.createFromAsset(getAssets(), "fzgl.ttf");
                break;
            case R.id.btnFzhl:
                typeface = Typeface.createFromAsset(getAssets(), "fzhl.ttf");
                break;
            case R.id.btnPop:
                typeface = Typeface.createFromAsset(getAssets(), "pop.ttf");
                break;
            case R.id.btnVIVALDII:
                typeface = Typeface.createFromAsset(getAssets(), "VIVALDII.TTF");
                break;
        }
        textView.setTypeface(typeface);
    }
}
