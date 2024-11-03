package com.example.tltdd_bai8;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    Button btnDanhBa, btnTinNhan;
    private static final int REQUEST1 = 1001;
    private static final int REQUEST2 = 1002;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnDanhBa = findViewById(R.id.btnDanhBa);

        btnDanhBa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            "" + "android.permission.READ_CONTACTS"}, REQUEST1);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, DanhBaActivity.class);
                    intent.setClassName("com.example.tltdd_bai8", "com.example.tltdd_bai8.DanhBaActivity");
                    startActivity(intent);
                }
            }
        });

        btnTinNhan = findViewById(R.id.btnTinNhan);

        btnTinNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            "" + "android.permission.READ_SMS"}, REQUEST2);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, DocTinNhanActivity.class);
                    intent.setClassName("com.example.tltdd_bai8", "com.example.tltdd_bai8.DocTinNhanActivity");
                    startActivity(intent);
                }
            }
        });
    }
}