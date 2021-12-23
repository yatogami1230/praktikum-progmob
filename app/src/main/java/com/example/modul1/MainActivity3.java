package com.example.modul1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    private FloatingActionButton fab;
    static RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        recyclerView =findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        setupRecyclerView();

        try{
            Intent intent = getIntent();
            String status = intent.getExtras().getString("Status");


            if (status.equals("add")) {
                Toast.makeText(this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
            } else if (status.equals("edit")) {
                Toast.makeText(this, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            if (userList.isEmpty()) {
                Toast.makeText(this, "Klik Tambah untuk Menambah User", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Klik User untuk Opsi Lain", Toast.LENGTH_SHORT).show();
            }
        }


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    static void setupRecyclerView(Context context, List<User> userList, RecyclerView recyclerView) {
        DatabaseHelper db = new DatabaseHelper(context);
        userList = db.selectUserData();

        // Meng set adapter Recycler View nya
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView() {
        DatabaseHelper db = new DatabaseHelper(this);
        userList = db.selectUserData();

        adapter = new RecyclerViewAdapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}