package com.example.modul1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
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

public class ModifyActivity extends AppCompatActivity {
    private EditText editNama;
    private EditText editEmail;
    private EditText editNowa;
    private EditText editAlamat;
    private CheckBox beginer, pro;
    private RadioGroup rg;
    private RadioButton perempuan;
    private RadioButton laki;
    private SeekBar umur;
    private TextView um;
    private Button update;


    private DatabaseHelper db;
    private User user;
    private int id;
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
        setContentView(R.layout.activity_modify);

        db = new DatabaseHelper(this);

        editNama=findViewById(R.id.nama);
        editEmail=findViewById(R.id.email);
        editNowa=findViewById(R.id.nowa);
        editAlamat=findViewById(R.id.alamat);
        beginer=findViewById(R.id.beginer);
        pro=findViewById(R.id.pro);
        rg=findViewById(R.id.rg);
        perempuan=findViewById(R.id.perempuan);
        laki=findViewById(R.id.laki);
        umur=findViewById(R.id.umur);
        um=findViewById(R.id.um);
        update=findViewById(R.id.update);

        Intent intent = getIntent();
        id = intent.getExtras().getInt("id");
        Nama = intent.getExtras().getString("Nama");
        Email = intent.getExtras().getString("Email");
        Nowa = intent.getExtras().getString("Nowa");
        Alamat = intent.getExtras().getString("Alamat");
        JenisKelamin= intent.getExtras().getString("JenisKelamin");
        Tingkat = intent.getExtras().getString("Tingkat");
        Umur = intent.getExtras().getString("Umur");

        editNama.setText(Nama);
        editEmail.setText(Email);
        editNowa.setText(Nowa);
        editAlamat.setText(Alamat);
        um.setText(Umur + " Tahun");
        setjeniskelaminSelected();
        settingkatSelected();
        umur.setProgress(Integer.parseInt(Umur));

        umur.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Umur = String.valueOf(progress);
                um.setText(Umur + " Tahun");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Nama = editNama.getText().toString().trim();
                Email = editEmail.getText().toString().trim();
                Nowa = editNowa.getText().toString().trim();
                Alamat = editAlamat.getText().toString().trim();
                JenisKelamin = getjeniskelaminSelected();
                Tingkat = gettingkatSelected();

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(ModifyActivity.this);
                builder.setTitle("Update Berhasil !!!");
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
                        Toast.makeText(getApplicationContext(), "Data Anda Berhasil Diupdate !", Toast.LENGTH_SHORT).show();

                        Intent layout2 = new Intent(ModifyActivity.this, MainActivity3.class);

                        user = new User();
                        user.setId(id);
                        user.setNama(Nama);
                        user.setEmail(Email);
                        user.setNowa(Nowa);
                        user.setAlamat(Alamat);
                        user.setJenisKelamin(JenisKelamin);
                        user.setTingkat(Tingkat);
                        user.setUmur(Umur);

                        db.update(user);

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

    private void settingkatSelected() {
        if (Tingkat.contains("Beginer")){
            beginer.setChecked(true);
        }
        if (Tingkat.contains("Pro")){
            pro.setChecked(true);
        }
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

    private void setjeniskelaminSelected() {
        if (JenisKelamin.equals("Laki-Laki")) {
            laki.setChecked(true);
        }
        if (JenisKelamin.equals("Perempuan")) {
            perempuan.setChecked(true);
        }
    }

    private String getjeniskelaminSelected(){
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