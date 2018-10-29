package zut.fly.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class query extends AppCompatActivity   implements View.OnClickListener{

    MyHelper myHelper;
    private Button btn_back;
    private TextView tshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        myHelper=new MyHelper(this);
        btn_back=(Button)findViewById(R.id.btn_back);
        tshow=(TextView)findViewById(R.id.tv_show);
        tshow.setMovementMethod(ScrollingMovementMethod.getInstance());
        btn_back.setOnClickListener(this);
        showdata();
    }

    private void showdata() {
        SQLiteDatabase db;
        int count=1;
        ContentValues values;
        db=myHelper.getReadableDatabase();
        Cursor cursor=db.query("information",null,null,null,null,null,null);
        if(cursor.getCount()==0){
            tshow.setText("");
            Toast.makeText(this,"没有数据",Toast.LENGTH_SHORT).show();
        }else {
            cursor.moveToFirst();
            tshow.setText(count+"、主题:"+cursor.getString(1)
                    +"\n"+"内容:"+cursor.getString(2)
                    +"\n"+"时间:"+cursor.getString(3));
            count++;

        }
        while(cursor.moveToNext()){
            tshow.append("\n\n"+count+"、主题:"+cursor.getString(1)
                    +"\n"+"内容:"+cursor.getString(2)
                    +"\n"+"时间:"+cursor.getString(3));
            count++;
        }
        cursor.close();
        db.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
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
