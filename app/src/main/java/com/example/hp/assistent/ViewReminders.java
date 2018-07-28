package com.example.hp.assistent;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ViewReminders extends AppCompatActivity {





    private static final String TAG = "ViewReminders";
    DatabaseHelper mDatabaseHelper;
    private ListView mListview;
    CustomAdapter customAdapter;
    Button recent,today,up_coming;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mListview = (ListView) findViewById(R.id.list);
        recent=(Button) findViewById(R.id.recent);
        today=(Button) findViewById(R.id.today);
        up_coming=(Button) findViewById(R.id.upcoming);
        mDatabaseHelper = new DatabaseHelper(this);

        recent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                populateListview(1);
            }

        });
        today.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                populateListview(2);
            }

        });
        up_coming.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                populateListview(3);
            }

        });


    }

    private void populateListview(int a) {
        Log.d(TAG, "populateListview:Displaying data in list ViewReminders ");
        Cursor data = mDatabaseHelper.getData(a);

        ArrayList<Todo> listData = new ArrayList<>();
        while (data.moveToNext())
        {
            Todo t = new Todo(data.getString(0), data.getString(1),data.getString(2),data.getString(3));
            listData.add(t);
        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        CustomAdapter adapter = new CustomAdapter(listData);
        mListview.setAdapter(adapter);
    }
}
