package com.example.day07_01;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final String DB_NAME = "users.db";
    static final String TB_NAME = "tb_users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



    /*public void createDB() {
        SQLiteDatabase db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        String sql = "create table " + TB_NAME + "("
                + "id integer ,"
                + "name varchar(20),"
                + "password varchar(20),"
                +" phone varchar(20),"
                +" email varchar(20))";
        db.execSQL(sql);
        sql="insert into "+TB_NAME+"(id, name,password,phone,email)values(20, 'zhangfei','123','68337799','zf@qq.com')";
        db.execSQL(sql);
        sql="insert into "+TB_NAME+"(name,password,phone,email)values('wangfei','123','68337799','zf@qq.com')";
        db.execSQL(sql);
        sql="insert into "+TB_NAME+"(name,password,phone,email)values('liuyifei','123','68337799','zf@qq.com')";
        db.execSQL(sql);
        sql="insert into "+TB_NAME+"(name,password,phone,email)values('coffee','123','68337799','zf@qq.com')";
        db.execSQL(sql);
    }*/

    public void createDB(View view) {
        SQLiteDatabase db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        String sql = "create table " + TB_NAME + " ( "
                + "id integer primary key AUTOINCREMENT,"
                + " name varchar,"
                + " password varchar,"
                + " phone varchar,"
                + " email varchar)";
        db.execSQL(sql);
        sql="insert into "+TB_NAME+"(name,password,phone,email)values('张飞','123','68337799','zf@qq.com')";
        db.execSQL(sql);
        sql="insert into "+TB_NAME+"(name,password,phone,email)values('王菲','123','68337799','zf@qq.com')";
        db.execSQL(sql);
        sql="insert into "+TB_NAME+"(name,password,phone,email)values('刘亦菲','123','68337799','zf@qq.com')";
        db.execSQL(sql);
        sql="insert into "+TB_NAME+"(name,password,phone,email)values('咖啡','123','68337799','zf@qq.com')";
        db.execSQL(sql);
    }

    /**
     * 删除记录
     * @param view
     */
    public void deleteRecorder(View view) {
        SQLiteDatabase db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        db.execSQL("delete from "+ TB_NAME +" where name like '%菲%'");
    }

    /**
     * 修改记录
     * @param view
     */
    public void updateRecorder(View view) {
        SQLiteDatabase db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        db.execSQL("update "+TB_NAME+" set name = '张飞飞'"+" where name = '张飞'" );
    }
    /**
     * 查询所有记录
     * @param view
     */
    public void queryAllRecorder(View view) {
        SQLiteDatabase db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        String sql = "select * from " + TB_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<User> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            list.add(new User(id, name, password, phone, email));
        }
        
        for (User user : list) {
            Log.i("main", user.toString());
            Toast.makeText(this, user.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 查询指定姓名的记录
     * @param view
     */
    public void queryRecorderByName(View view) {
        SQLiteDatabase db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

       /* String sql = "select * from " + TB_NAME + " where name like '%菲%'";
        Cursor cursor = db.rawQuery(sql, null);*/
        String sql = "select * from " + TB_NAME + " where name like ?";
        Cursor cursor = db.rawQuery(sql, new String[]{"%菲%"});
        ArrayList<User> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            list.add(new User(id, name, password, phone, email));
        }

        for (User user : list) {
            Log.i("main", user.toString());
        }
    }


}
