package cn.ucai.day04_04;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 设置日期对话框
     * @param view
     */
    public void setDatePickerDialog(View view) {
        Calendar c = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(MainActivity.this, "选择的时间："+year+"/"+(month+1)+"/"+dayOfMonth,
                        Toast.LENGTH_SHORT).show();
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.setTitle("设置考试日期");
        dialog.show();
    }

    /**
     * 设置时间对话框
     * @param view
     */
    public void setTimePickerDialog(View view) {
        Calendar c = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(MainActivity.this, "选择的考试时间为："+hourOfDay+":"+minute, Toast.LENGTH_SHORT).show();
            }
        }, c.get(Calendar.HOUR),c.get(Calendar.MINUTE), true);
        dialog.setTitle("设置考试时间");
        dialog.show();

    }
}
