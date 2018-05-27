package com.example.day07_04;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getContactsInfo();
    }

    private void getContactsInfo() {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            StringBuilder sb = new StringBuilder();
            int id = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            sb.append("id: ").append(id);
            sb.append(", displayName: ").append(cursor.getString(cursor.getColumnIndex
                    (ContactsContract.Contacts.DISPLAY_NAME)));

            // 读取电话
            Cursor cPhone = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
            sb.append(", phone:");
            while (cPhone.moveToNext()) {
                sb.append(cPhone.getString(cPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)))
                        .append(",");
            }

            Cursor cEmail = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=" + id, null, null);
            sb.append(", email: ");
            while (cEmail.moveToNext()) {
                sb.append(cEmail.getString(cEmail.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS)))
                        .append(",");
            }
            sb.deleteCharAt(sb.length() - 1);


            Log.i("main", sb.toString());

        }
    }
}
