package cn.ucai.day04_ex01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = (Button) findViewById(R.id.button);
        Log.i("main", button.toString());
        setContentView(R.layout.activity_main);
    }
}

