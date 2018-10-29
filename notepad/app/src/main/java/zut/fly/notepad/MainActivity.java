package zut.fly.notepad;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    MyHelper myHelper;
    private Button btn1;
    private Button btn2;
    private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        myHelper=new MyHelper(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SQLiteDatabase db;
        switch (v.getId()) {
            case R.id.btn1:
                Intent intent1 = new Intent(this, note.class);
                startActivity(intent1);
                break;
            case R.id.btn2:
                Intent intent2 = new Intent(this, query.class);
                startActivity(intent2);
                break;
            case R.id.btn3:
                db=myHelper.getWritableDatabase();
                db.delete("information",null,null);
                Toast.makeText(this,"记事本已清空",Toast.LENGTH_SHORT).show();
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
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
