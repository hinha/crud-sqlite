package com.root.hinha.crudsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LihatDataActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbhelper;
    Button btn1;
    TextView txt1, txt2, txt3, txt4, txt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);
        dbhelper = new DataHelper(this);
        txt1 =  (TextView) findViewById(R.id.textView1);
        txt2 =  (TextView) findViewById(R.id.textView2);
        txt3 =  (TextView) findViewById(R.id.textView3);
        txt4 =  (TextView) findViewById(R.id.textView4);
        txt5 =  (TextView) findViewById(R.id.textView5);

        SQLiteDatabase db = dbhelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama='"+
                getIntent().getStringExtra("nama")+
                "'", null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            txt1.setText(cursor.getString(0).toString());
            txt2.setText(cursor.getString(1).toString());
            txt3.setText(cursor.getString(2).toString());
            txt4.setText(cursor.getString(3).toString());
            txt5.setText(cursor.getString(4).toString());
        }
        btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
