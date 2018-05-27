package cn.ucai.day04_06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    EditText metQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initView();

        // 注册上下文菜单
        registerForContextMenu(metQuestion);


    }


    private void initView() {
        metQuestion = (EditText) findViewById(R.id.etQuestion);
    }

    /**
     * 创建上下文菜单
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.menu_question, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    /**
     * 处理上下文菜单的选择
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_qs01:
                Toast.makeText(this, "第1题被选择", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mi_qs02:
                Toast.makeText(this, "第2题被选择", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mi_qs03:
                Toast.makeText(this, "第3题被选择", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mi_qs04:
                Toast.makeText(this, "第4题被选择", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mi_qs05:
                Toast.makeText(this, "第5题被选择", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }
}
