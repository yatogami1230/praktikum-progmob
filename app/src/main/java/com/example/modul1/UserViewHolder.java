package com.example.modul1;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.List;

public class UserViewHolder extends RecyclerView.ViewHolder{
    public BreakIterator listNama;
    TextView ListNama;
    TextView ListTingkat;
    TextView ListJK;
    private CardView listUser;
    private Context context;
    private List<User> userList;
    private int id;
    private String Nama;
    private String Email;
    private String Nowa;
    private String Alamat;
    private String JenisKelamin;
    private String Tingkat;
    private String Umur;

    public UserViewHolder(View itemView){
        super(itemView);
        context = itemView.getContext();

        ListNama = itemView.findViewById(R.id.listnama);
        ListTingkat = itemView.findViewById(R.id.listtingkat);
        ListJK = itemView.findViewById(R.id.listjk);
        listUser = itemView.findViewById(R.id.row_item);

        listUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {alertAction(context, getAdapterPosition());}
        });
    }

    private void alertAction(Context context,int position) {
        String[] option ={"Detail", "Edit", "Delete"};
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        DatabaseHelper db =new DatabaseHelper(context);
        userList = db.selectUserData();

        id = userList.get(position).getId();
        Nama = userList.get(position).getNama();
        Email = userList.get(position).getEmail();
        Nowa = userList.get(position).getNowa();
        Alamat = userList.get(position).getAlamat();
        JenisKelamin = userList.get(position).getJenisKelamin();
        Tingkat = userList.get(position).getTingkat();
        Umur = userList.get(position).getUmur();

        builder.setTitle("Pilih Opsi");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                switch (i){
                    case 0:
                        Intent mainActivity2 = new Intent(context, MainActivity2.class);
                        mainActivity2.putExtra("id",id);
                        mainActivity2.putExtra("Nama",Nama);
                        mainActivity2.putExtra("Email",Email);
                        mainActivity2.putExtra("Nowa",Nowa);
                        mainActivity2.putExtra("Alamat",Alamat);
                        mainActivity2.putExtra("JenisKelamin",JenisKelamin);
                        mainActivity2.putExtra("Tingkat",Tingkat);
                        mainActivity2.putExtra("Umur",Umur);

                        context.startActivity(mainActivity2);
                        break;

                    case 1:
                        Intent  modifyActivity = new Intent(context,  ModifyActivity.class);
                        modifyActivity.putExtra("id",id);
                        modifyActivity.putExtra("Nama",Nama);
                        modifyActivity.putExtra("Email",Email);
                        modifyActivity.putExtra("Nowa",Nowa);
                        modifyActivity.putExtra("Alamat",Alamat);
                        modifyActivity.putExtra("JenisKelamin",JenisKelamin);
                        modifyActivity.putExtra("Tingkat",Tingkat);
                        modifyActivity.putExtra("Umur",Umur);

                        context.startActivity( modifyActivity);
                        break;

                    case 2:
                        DatabaseHelper db = new DatabaseHelper(context);
                        db.delete(userList.get(position).getId());

                        userList = db.selectUserData();
                        MainActivity3.setupRecyclerView(context, userList, MainActivity3.recyclerView);

                        Toast.makeText(context, "Data Berhasil Dihapus !", Toast.LENGTH_SHORT);
                        break;

                }

            }
        });
        builder.show();

    }
}
