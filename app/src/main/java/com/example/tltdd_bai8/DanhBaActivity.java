package com.example.tltdd_bai8;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tltdd_bai8.models.Contact;

import java.util.ArrayList;

public class DanhBaActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Contact> contacts;
    ArrayAdapter<Contact> adapter;
    private static final int REQUEST_CODE = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danh_ba);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv = findViewById(R.id.listview);
        contacts = new ArrayList<>();

        showDSDanhBa();
        getData();
    }

//    private void getData() {
//        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//        String tencotname = ContactsContract.Contacts.DISPLAY_NAME;
//        String tencotphone = ContactsContract.CommonDataKinds.Phone.NUMBER;
//        int vtname = cursor.getColumnIndex(tencotname);
//        int vtphone = cursor.getColumnIndex(tencotphone);
//        Contact contact = new Contact(cursor.getString(vtname), cursor.getString(vtphone));
//        contacts.add(contact);
//        adapter.notifyDataSetChanged();
//    }

    private void getData() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        String tencotname = ContactsContract.Contacts.DISPLAY_NAME;
                        String tencotphone = ContactsContract.CommonDataKinds.Phone.NUMBER;

                        int vtname = cursor.getColumnIndex(tencotname);
                        int vtphone = cursor.getColumnIndex(tencotphone);

                        if (vtname != -1 && vtphone != -1) {
                            String name = cursor.getString(vtname);
                            String phone = cursor.getString(vtphone);

                            Contact contact = new Contact(name, phone);
                            contacts.add(contact);
                        }
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        } else {
            // Xử lý trường hợp không có dữ liệu
            Log.e("getData", "Cursor is null");
        }

        // Cập nhật adapter sau khi thêm dữ liệu
        adapter.notifyDataSetChanged();
    }


    private void showDSDanhBa() {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        lv.setAdapter(adapter);
    }
}