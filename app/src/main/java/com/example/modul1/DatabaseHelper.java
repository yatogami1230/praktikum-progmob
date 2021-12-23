package com.example.modul1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "formbulutangkis";
    private static final String TABLE_NAME = "tbl_bulutangkis";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NO_WA = "nowa";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_JENISKELAMIN = "jeniskelamin";
    private static final String KEY_TINGKAT = "tingkat";
    private static final String KEY_UMUR = "umur";

    public DatabaseHelper(@Nullable Context context) { super(context, DB_NAME, null, DB_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTable = "CREATE TABLE " + TABLE_NAME +
                " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NAMA + " TEXT, " +
                KEY_EMAIL + " TEXT, " +
                KEY_NO_WA + " TEXT, " +
                KEY_ALAMAT + " TEXT, " +
                KEY_JENISKELAMIN + " TEXT, " +
                KEY_TINGKAT + " TEXT, " +
                KEY_UMUR + " TEXT " + ")";
        sqLiteDatabase.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;

        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void insert (User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAMA,user.getNama());
        values.put(KEY_EMAIL,user.getEmail());
        values.put(KEY_NO_WA,user.getNowa());
        values.put(KEY_ALAMAT,user.getAlamat());
        values.put(KEY_JENISKELAMIN,user.getJenisKelamin());
        values.put(KEY_TINGKAT,user.getTingkat());
        values.put(KEY_UMUR,user.getUmur());

        db.insert(TABLE_NAME, null, values);
    }

    public List<User> selectUserData() {
        ArrayList<User> users = new ArrayList<User>();

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {KEY_ID, KEY_NAMA, KEY_EMAIL, KEY_NO_WA, KEY_ALAMAT, KEY_JENISKELAMIN,
                KEY_TINGKAT, KEY_UMUR};

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

        while (cursor.moveToNext()) {

            int id =cursor.getInt(0);
            String Nama = cursor.getString(1);
            String Email = cursor.getString(2);
            String Nowa = cursor.getString(3);
            String Alamat = cursor.getString(4);
            String JenisKelamin = cursor.getString(5);
            String Tingkat = cursor.getString(6);
            String Umur = cursor.getString(7);

            User user = new User();
            user.setId(id);
            user.setNama(Nama);
            user.setEmail(Email);
            user.setNowa(Nowa);
            user.setAlamat(Alamat);
            user.setJenisKelamin(JenisKelamin);
            user.setTingkat(Tingkat);
            user.setUmur(Umur);

            users.add(user);
        }
        return users;
    }

    public void update(User user){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAMA,user.getNama());
        values.put(KEY_EMAIL,user.getEmail());
        values.put(KEY_NO_WA,user.getNowa());
        values.put(KEY_ALAMAT,user.getAlamat());
        values.put(KEY_JENISKELAMIN,user.getJenisKelamin());
        values.put(KEY_TINGKAT,user.getTingkat());
        values.put(KEY_UMUR,user.getUmur());

        String whereClause = KEY_ID + " = '" + user.getId() +"'";

        db.update(TABLE_NAME, values,whereClause,null);
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();

        String whereClause = KEY_ID + " = '" + id + "'";

        db.delete(TABLE_NAME, whereClause, null);
    }

}
