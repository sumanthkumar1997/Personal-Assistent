package com.example.hp.assistent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;

public class HomeActivity extends AppCompatActivity {


    public static final String DATABASE_NAME = "mydatabase";

    SQLiteDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Button add = (Button) findViewById(R.id.addreminder);
        final Button view = (Button) findViewById(R.id.view);
        final Button edit = (Button) findViewById(R.id.edit);
        final Button delete = (Button) findViewById(R.id.delete);
        final Button exit = (Button) findViewById(R.id.exit);

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                addpage();
            }

        });
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                delePage();
            }

        });

        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                viewpage();
            }

        });

        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                editpage();
            }

        });

        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                finish();
            }

        });
    }


    private void editpage() {
        Intent in = new Intent(this, EditList.class);
        startActivity(in);

    }

    private void viewpage() {

        Intent in = new Intent(this, ViewReminders.class);
        startActivity(in);


    }

    private void addpage() {
        Intent in = new Intent(this, AddActivity.class);
        startActivity(in);

    }
    private void delePage()
    {
        Intent in = new Intent(this, DeleteEvent.class);
        startActivity(in);


    }
}
