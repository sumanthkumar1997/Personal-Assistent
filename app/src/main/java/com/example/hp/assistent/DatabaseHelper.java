package com.example.hp.assistent;

import android.content.ContentValues;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * Created by HP on 18-07-2018.
 */

//class DatabaseHelper {
//}


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "reminder";
    private static final String Col1 = "id";
    private static final String Col2 = "subject";
    private static final String Col3 = "date";
    private static final String Col4 = "descr";
    String date;

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + Col2 + " TEXT," + Col3 + " TEXT,"
                + Col4 + " TEXT)";
        db.execSQL(createTable);
    }

    public boolean addData(String item1, String item2, String item3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col2, item1);
        contentValues.put(Col3, item2);
        contentValues.put(Col4, item3);

        Log.d(TAG, "addData: " + item1 + " " + item2 + " " + item3);


        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public void update(String item0,String item1, String item2, String item3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col1, item0);
        contentValues.put(Col2, item1);
        contentValues.put(Col3, item2);
        contentValues.put(Col4, item3);

        String query;
        query=("UPDATE "+TABLE_NAME+" SET "+Col2+" = '"+item1+"' , "+Col3+" = '"+item2+"' , "+Col4+" = '"+item3+"' WHERE "+Col1+" = '"+item0+"'");

        Log.d(TAG, "modify: " + item1 + " " + item2 + " " + item3);


        db.execSQL(query);

       /* if (result == -1)
            return false;
        else
            return true;*/
    }

    public  void delete(String id)
    {
        String q;
        SQLiteDatabase db = this.getWritableDatabase();
        q=("DELETE FROM "+TABLE_NAME+" WHERE "+Col1+" = '"+id+"'");
        db.execSQL(q);
    }


    public Cursor getData(int a)

    {
        date = getDate();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = ("SELECT * FROM " + TABLE_NAME);
        if (a == 1) {
            query = ("SELECT * FROM " + TABLE_NAME + " WHERE " + Col3 + "<" + "'" + date + "'");

        } else if (a == 2) {
            query = ("SELECT * FROM " + TABLE_NAME + " WHERE " + Col3 + "=" + "'" + date + "'");
        } else if (a == 3) {

            query = ("SELECT * FROM " + TABLE_NAME + " WHERE " + Col3 + ">" + "'" + date + "'");
        }

        Cursor data = db.rawQuery(query, null);
        return data;
    }


    private String getDate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println(c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String newDate = df.format(c);
        System.out.println(newDate);
        return newDate;
    }

}
