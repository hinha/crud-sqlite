package com.root.hinha.crudsqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.database.Cursor;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] daftar;
    ListView ListView01;
    Menu menu;

    protected Cursor cursor;
    DataHelper dbcenter;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, BuatDataActivity.class);
                startActivity(intent);
            }
        });
        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc); // data posisi pindah ke 1
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = (ListView) findViewById(R.id.listView1); // declare func ListView
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));     // pt01
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Biodata", "Update Biodata", "Hapus Biodata"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int item) {
                       switch (item) {
                           case 0:
                               Intent i = new Intent(getApplicationContext(), LihatDataActivity.class);
                               i.putExtra("nama", selection);
                               startActivity(i);
                               break;
                           case 1:
                               Intent i2 = new Intent(getApplicationContext(), UpdateDataActivity.class);
                               i2.putExtra("nama", selection);
                               startActivity(i2);
                               break;
                           case 2:
                               SQLiteDatabase db = dbcenter.getWritableDatabase();
                               db.execSQL("delete from biodata where nama = '"+ selection + "'");
                               RefreshList();
                               break;
                       }
                   }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();        // pt01
    }

//    public boolean onCreateOptionMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu, menu);
//        return true;
//    }


}
