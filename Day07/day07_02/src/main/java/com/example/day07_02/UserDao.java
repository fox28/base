package com.example.day07_02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/2/21.
 */

public class UserDao extends SQLiteOpenHelper {
    static final String DB_NAME = "users2.db";
    static final String TB_NAME = "tb_users";
    static final String KEY_ID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_PASSWORD = "password";
    static final String KEY_PHONE = "phone";
    static final String KEY_EMAIL = "email";

    public UserDao(Context context,  int version) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TB_NAME + "("
                + " id integer primary key AUTOINCREMENT,"
                + " name varchar"
                + " password varchar"
                + " phone varchar"
                + " email varchar)";
        db.execSQL(sql);

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, "张飞");
        values.put(KEY_PASSWORD, "123");
        values.put(KEY_PHONE, "665577881");
        values.put(KEY_EMAIL, "zf@qq.com");
        db.insert(TB_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_NAME, "王菲");
        values.put(KEY_PASSWORD, "123");
        values.put(KEY_PHONE, "665577882");
        values.put(KEY_EMAIL, "wf@qq.com");
        db.insert(TB_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_NAME, "刘亦菲");
        values.put(KEY_PASSWORD, "123");
        values.put(KEY_PHONE, "665577883");
        values.put(KEY_EMAIL, "lyf@qq.com");
        db.insert(TB_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_NAME, "林心如");
        values.put(KEY_PASSWORD, "123");
        values.put(KEY_PHONE, "665577884");
        values.put(KEY_EMAIL, "lxr@qq.com");
        db.insert(TB_NAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<User> queryAll() {
        ArrayList<User> usersList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TB_NAME, null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            String password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));
            String phone = cursor.getString(cursor.getColumnIndex(KEY_PHONE));
            String email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL));

            usersList.add(new User(id, name, password, phone, email));
        }

        return usersList;
    }

    public boolean deleteRecorder(String name) {
        SQLiteDatabase db = getWritableDatabase();
        int count = db.delete(TB_NAME, KEY_NAME + " like ? ", new String[]{"%" + name + "%"});
        return count>0;
    }

    public boolean updateRecord(User user){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_PHONE, user.getPhone());
        values.put(KEY_EMAIL, user.getEmail());
        int i = db.update(TB_NAME, values, " name='张飞'", null);
        return i>0;
    }

    public List<User> queryRecordByName(String name) {
        ArrayList<User> usersList = new ArrayList<>();


        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TB_NAME, new String[]{KEY_NAME}, "name like ?", new String[]{"%" + name + "%"}, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String name02 = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            String password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));
            String phone = cursor.getString(cursor.getColumnIndex(KEY_PHONE));
            String email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL));

            usersList.add(new User(id, name02, password, phone, email));
        }

        return usersList;
    }
}
