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

public class EditList extends AppCompatActivity {

    private static final String TAG = "EditList";
    DatabaseHelper mDatabaseHelper;
    public ListView mListview;
    CustomAdapter customAdapter;
    //    Button recent, today, up_coming;
    LinearLayout linearLayout;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
//        setContentView(R.layout.editdisplay);

        mListview = (ListView) findViewById(R.id.list);
        Button recent = (Button) findViewById(R.id.btn_recent);
        Button today = (Button) findViewById(R.id.btn_today);
        Button up_coming = (Button) findViewById(R.id.btn_upcoming);
        linearLayout = (LinearLayout) findViewById(R.id.edit_layout);
        //  final TextView id_disp=(TextView) findViewById(R.id.id_disp);

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

       /* linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {

               id=id_disp.getText().toString();
                System.out.println(id);

                edit();
            }

        });*/

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
        CustomAdapter adapter = new CustomAdapter(listData, new ClickCallback() {
            @Override
            public void onItemClick(int position, Todo todo) {
                Toast.makeText(EditList.this, todo.getDisp2(), Toast.LENGTH_SHORT).show();
                String id=todo.getId();
                String sub=todo.getDisp1();
                String date=todo.getDisp2();
                String desc=todo.getDisp3();


                Intent intent=new Intent(EditList.this,EditPage.class);
                intent.putExtra("id",id);
                intent.putExtra("sub",sub);
                intent.putExtra("date",date);
                intent.putExtra("desc",desc);
                startActivity(intent);
                finish();
            }
        });
        mListview.setAdapter(adapter);
    }

}

