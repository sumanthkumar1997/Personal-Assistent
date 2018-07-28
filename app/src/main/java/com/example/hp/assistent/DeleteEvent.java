package com.example.hp.assistent;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class DeleteEvent extends AppCompatActivity {

    private static final String TAG = "delete_page";
    DatabaseHelper mDatabaseHelper;
    public ListView mListview;
    CustomAdapter customAdapter;
    //    Button recent, today, up_coming;
    LinearLayout linearLayout;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_page);

        mListview = (ListView) findViewById(R.id.list);
        Button recent = (Button) findViewById(R.id.btn_recent);
        Button today = (Button) findViewById(R.id.btn_today);
        Button up_coming = (Button) findViewById(R.id.btn_upcoming);


        CustomAdapter customAdapter1;
        mDatabaseHelper = new DatabaseHelper(this);


        recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateListview(1);
            }
        });

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateListview(2);
            }
        });

        up_coming.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        populateListview(3);
                    }
                }
        );

    }


    private void populateListview(int a) {
        Log.d(TAG, "populateListview:Displaying data in list ViewReminders ");
        Cursor data = mDatabaseHelper.getData(a);
//
        ArrayList<Todo> listData = new ArrayList<>();
        while (data.moveToNext()) {
            Todo t = new Todo(data.getString(0), data.getString(1), data.getString(2), data.getString(3));
            listData.add(t);
        }
//
        CustomAdapterDeletePage adapter = new CustomAdapterDeletePage(listData, new ClickCallback() {
            @Override
            public void onItemClick(int position, Todo todo) {
                Toast.makeText(DeleteEvent.this, "Deleted  \""+todo.getDisp1().toUpperCase()+"\"  Successfully", Toast.LENGTH_LONG).show();
                String id = todo.getId();

                mDatabaseHelper.delete(id);
                Intent intent = new Intent(DeleteEvent.this, HomeActivity.class);
                startActivity(intent);
                finish();

            }
        });
        mListview.setAdapter(adapter);

    }

}
