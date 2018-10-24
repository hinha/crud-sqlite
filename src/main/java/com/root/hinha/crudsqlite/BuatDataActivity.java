package com.root.hinha.crudsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuatDataActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbhelper;
    Button btn1, btn2;
    EditText txt1, txt2, txt3, txt4, txt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_data);
        dbhelper = new DataHelper(this);
        txt1 =  (EditText) findViewById(R.id.editText1);
        txt2 =  (EditText) findViewById(R.id.editText2);
        txt3 =  (EditText) findViewById(R.id.editText3);
        txt4 =  (EditText) findViewById(R.id.editText4);
        txt5 =  (EditText) findViewById(R.id.editText5);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                db.execSQL("insert into biodata(no, nama, tgl, jk, alamat) values('" +
                        txt1.getText().toString() + "','" +
                        txt2.getText().toString() + "','" +
                        txt3.getText().toString() + "','" +
                        txt4.getText().toString() + "','" +
                        txt5.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList(); // panggil Main Activity
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
