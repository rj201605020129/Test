package zut.fly.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class note extends AppCompatActivity  implements View.OnClickListener{

    MyHelper myHelper;
    private EditText et_thame;
    private EditText et_text;
    private Button btn_back;
    private Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        myHelper=new MyHelper(this);
        et_thame=(EditText)findViewById(R.id.et_thame);
        et_text=(EditText)findViewById(R.id.et_text);
        btn_back=(Button)findViewById(R.id.btn_back);
        btn_save=(Button)findViewById(R.id.btn_save);
        et_text.setMovementMethod(ScrollingMovementMethod.getInstance());
        btn_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String text;
        String thame;
        String time;
        SQLiteDatabase db;
        ContentValues values;
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_save:
                SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日 HH:mm:ss");
                Date curDate =  new Date(System.currentTimeMillis());
                time = formatter.format(curDate);
                text = et_text.getText().toString();
                thame=et_thame.getText().toString();
                db=myHelper.getWritableDatabase();
                values=new ContentValues();
                values.put("thame",thame);
                values.put("text",text);
                values.put("time",time);
                db.insert("information",null,values);
                Toast.makeText(this,"信息已添加",Toast.LENGTH_SHORT).show();
                db.close();
                break;
        }
    }

    class MyHelper extends SQLiteOpenHelper {
        public MyHelper(Context context) {
            super(context, "notepad.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE information(_id INTEGER PRIMARY KEY AUTOINCREMENT,thame VARCHAR(100) ,text VARCHAR(1000),time VARCHAR(50))");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
