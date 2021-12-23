package com.example.modul1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class MainActivity extends AppCompatActivity{
    private EditText nama;
    private EditText email;
    private EditText nowa;
    private EditText alamat;
    private CheckBox beginer, pro;
    private RadioGroup rg;
    private RadioButton perempuan;
    private RadioButton laki;
    private SeekBar umur;
    private TextView um;
    private String nilaiUmur;
    private Button daftar;


    private DatabaseHelper db;
    private User user;
    private String Nama;
    private String Email;
    private String Nowa;
    private String Alamat;
    private String JenisKelamin;
    private String Tingkat;
    private String Umur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        nama=findViewById(R.id.nama);
        email=findViewById(R.id.email);
        nowa=findViewById(R.id.nowa);
        alamat=findViewById(R.id.alamat);
        beginer=findViewById(R.id.beginer);
        pro=findViewById(R.id.pro);
        rg=findViewById(R.id.rg);
        perempuan=findViewById(R.id.perempuan);
        laki=findViewById(R.id.laki);
        umur=findViewById(R.id.umur);
        um=findViewById(R.id.um);
        daftar=findViewById(R.id.daftar);

        umur.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                nilaiUmur = String.valueOf(progress);
                um.setText(nilaiUmur + " Tahun");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Nama = nama.getText().toString();
                Email = email.getText().toString();
                Nowa = nowa.getText().toString();
                Alamat = alamat.getText().toString();
                JenisKelamin = getjeniskelaminSelected();
                Tingkat = gettingkatSelected();
                Umur = nilaiUmur;

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
                builder.setTitle("Pendaftaran Berhasil !!!");
                builder.setMessage("Nama: " + Nama + "\n" +
                        "Email:  " + Email + "\n" +
                        "No Wa: " + Nowa + "\n" +
                        "Alamat: " + Alamat + "\n" +
                        "Jenis Kelamin: " + JenisKelamin + "\n" +
                        "Tingkatan: " + Tingkat +
                        "Umur: " + Umur);

                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Data Anda Berhasil Didaftarkan !", Toast.LENGTH_SHORT).show();

                        Intent layout2 = new Intent(MainActivity.this, MainActivity3.class);

                        user = new User();
                        user.setNama(Nama);
                        user.setEmail(Email);
                        user.setNowa(Nowa);
                        user.setAlamat(Alamat);
                        user.setJenisKelamin(JenisKelamin);
                        user.setTingkat(Tingkat);
                        user.setUmur(Umur);

                        db.insert(user);

                        startActivity(layout2);
                        finish();
                    }
                });

                builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            }
        });
    }

    private String gettingkatSelected() {
        String tingkat = "";

        if (beginer.isChecked()){
            tingkat += beginer.getText().toString() + "\n";
        }
        if (pro.isChecked()){
            tingkat += pro.getText().toString() + "\n";
        }

        return tingkat;
    }

    private String getjeniskelaminSelected() {
        String jeniskelamin = "";

        int selectedId = rg.getCheckedRadioButtonId();

        if (selectedId == laki.getId()){
            jeniskelamin = "Laki-Laki";
        }
        if (selectedId == perempuan.getId()){
            jeniskelamin = "Perempuan";
        }

        return jeniskelamin;
    }
}