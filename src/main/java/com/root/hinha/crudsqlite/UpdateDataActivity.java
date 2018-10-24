package com.root.hinha.crudsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDataActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbhelper;
    Button btn1, btn2;
    EditText txt1, txt2, txt3, txt4, txt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        dbhelper = new DataHelper(this);
        txt1 =  (EditText) findViewById(R.id.editText1);
        txt2 =  (EditText) findViewById(R.id.editText2);
        txt3 =  (EditText) findViewById(R.id.editText3);
        txt4 =  (EditText) findViewById(R.id.editText4);
        txt5 =  (EditText) findViewById(R.id.editText5);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama='"+
                getIntent().getStringExtra("nama")+
                "'", null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            txt1.setText(cursor.getString(0).toString());
            txt1.setText(cursor.getString(1).toString());
            txt1.setText(cursor.getString(2).toString());
            txt1.setText(cursor.getString(3).toString());
            txt1.setText(cursor.getString(4).toString());
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                db.execSQL("UPDATE biodata SET nama='" +
                        txt2.getText().toString()+"', tgl='" +
                        txt3.getText().toString()+"', jk='"+
                        txt4.getText().toString()+"', alamat='"+
                        txt5.getText().toString()+"', WHERE no='"+
                        txt1.getText().toString()+ "'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}
