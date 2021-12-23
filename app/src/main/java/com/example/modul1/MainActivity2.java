package com.example.modul1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    TextView tampilNama;
    TextView tampilEmail;
    TextView tampilNowa;
    TextView tampilAlamat;
    TextView tampilJK;
    TextView tampilTingkat;
    TextView tampilUmur;
    String Nama;
    String Email;
    String Nowa;
    String Alamat;
    String JenisKelamin;
    String Tingkat;
    String Umur;

    private DatabaseHelper db;
    private List<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = new DatabaseHelper(this);
        userList = db.selectUserData();

        tampilNama = findViewById(R.id.TampilNama);
        tampilEmail = findViewById(R.id.TampilEmail);
        tampilNowa = findViewById(R.id.TampilNowa);
        tampilAlamat = findViewById(R.id.TampilAlamat);
        tampilJK = findViewById(R.id.TampilJK);
        tampilTingkat = findViewById(R.id.TampilTingkat);
        tampilUmur = findViewById(R.id.TampilUmur);

        Nama = userList.get(userList.size()-1).getNama();
        Email = userList.get(userList.size()-1).getEmail();
        Nowa = userList.get(userList.size()-1).getNowa();
        Alamat = userList.get(userList.size()-1).getAlamat();
        JenisKelamin = userList.get(userList.size()-1).getJenisKelamin();
        Tingkat = userList.get(userList.size()-1).getTingkat();
        Umur = userList.get(userList.size()-1).getUmur();

        tampilNama.setText(Nama);
        tampilEmail.setText(Email);
        tampilNowa.setText(Nowa);
        tampilAlamat.setText(Alamat);
        tampilJK.setText(JenisKelamin);
        tampilTingkat.setText(Tingkat);
        tampilUmur.setText(Umur);

    }

    public void Kembali (View view) {
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Tampilan Biodata Atlet Dimulai",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Tampilan Biodata Atlet Sedang Berjalan",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Tampilan Biodata Atlet Berhenti Sementara",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Tampilan Biodata Atlet Berhenti",Toast.LENGTH_SHORT).show();
    }
}