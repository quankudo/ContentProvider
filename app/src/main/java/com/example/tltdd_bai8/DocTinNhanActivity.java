package com.example.tltdd_bai8;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tltdd_bai8.adapters.AdapterTinNhan;
import com.example.tltdd_bai8.models.TinNhan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DocTinNhanActivity extends AppCompatActivity {
    private static final int REQUEST2 = 1002;
    ListView lvDocTinNhan;
    ArrayList<TinNhan> dsTinNhan;
    AdapterTinNhan adapterTinNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doc_tin_nhan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addControll();
        DocToanBoTinNhan();
    }

//    private void DocToanBoTinNhan() {
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        Uri uri = Uri.parse("content://sms/inbox");
//        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//        dsTinNhan.clear();
//        while (cursor.moveToNext()) {
//            int indexPhoneNumber = cursor.getColumnIndex("address");
//            int indexTimeStamp = cursor.getColumnIndex("date");
//            int indexBody = cursor.getColumnIndex("body");
//
//            String phoneNumber = cursor.getString(indexPhoneNumber);
//            String timeStamp = cursor.getString(indexTimeStamp);
//            String body = cursor.getString(indexBody);
//            dsTinNhan.add(new TinNhan(phoneNumber, timeStamp, body));
//            adapterTinNhan.notifyDataSetChanged();
//        }
//    }

    private void DocToanBoTinNhan() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            try {
                dsTinNhan.clear();
                while (cursor.moveToNext()) {
                    int indexPhoneNumber = cursor.getColumnIndex("address");
                    int indexTimeStamp = cursor.getColumnIndex("date");
                    int indexBody = cursor.getColumnIndex("body");

                    String phoneNumber = indexPhoneNumber != -1 ? cursor.getString(indexPhoneNumber) : "Không rõ";
                    String timeStamp = indexTimeStamp != -1 ? cursor.getString(indexTimeStamp) : "";
                    String body = indexBody != -1 ? cursor.getString(indexBody) : "Không có nội dung";

                    String formattedTime = timeStamp.isEmpty() ? "" : sdf.format(new Date(Long.parseLong(timeStamp)));

                    dsTinNhan.add(new TinNhan(phoneNumber, formattedTime, body));
                }
                adapterTinNhan.notifyDataSetChanged();
            } finally {
                cursor.close();
            }
        } else {
            Log.e("DocToanBoTinNhan", "Cursor is null");
        }
    }


    private void addControll() {
        lvDocTinNhan = findViewById(R.id.lv_DocTinNhan);
        dsTinNhan = new ArrayList<>();
        adapterTinNhan = new AdapterTinNhan(DocTinNhanActivity.this, R.layout.item_tinnhan, dsTinNhan);
        lvDocTinNhan.setAdapter(adapterTinNhan);
    }
}