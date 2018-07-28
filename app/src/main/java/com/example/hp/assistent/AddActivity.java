package com.example.hp.assistent;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class AddActivity extends AppCompatActivity {

    private static final String TAG = "AddActivity";
    String myDate=null;
    String newDate, mymonth, d;
    Date mDate;
    int i = 0;

    DatabaseHelper mDatabaseHeper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText subject = (EditText) findViewById(R.id.subject);
        final CalendarView date = (CalendarView) findViewById(R.id.calender);
        final EditText desc = (EditText) findViewById(R.id.description);
        Button save = (Button) findViewById(R.id.save);
        mDatabaseHeper = new DatabaseHelper(this);

        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if ((month + 1) > 9) {
                    mymonth = String.valueOf(month + 1);
                } else {
                    mymonth = String.valueOf(i) + String.valueOf(month + 1);
                }
                if (dayOfMonth > 9) {
                    d = String.valueOf(dayOfMonth);
                } else {
                    d = String.valueOf(i) + String.valueOf(dayOfMonth);
                }
                myDate = String.valueOf(year) + "-" + mymonth + "-" + d;
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                //newDate=df.format(myDate).toString();
                //System.out.println(newDate);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entry1 = subject.getText().toString();
                String entry = DateHelper.convertDate(date.getDate());
                String entry2 = myDate;
                String entry3 = desc.getText().toString();


                if (entry1.length()==0)
                    toastMessege("Subject can't be empty\nUnsuccessfull ");
                else if (entry2==null)
                    toastMessege("Date is Mandatory\nUnsuccessfull");
                else if ((entry1.length() != 0) && (entry2.length() != 0))
                {
                    AddData(entry1, entry2, entry3);
                    Intent i = new Intent(AddActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        });


    }

    public void AddData(String entry1, String entry2, String entry3) {
        boolean insertData = mDatabaseHeper.addData(entry1, entry2, entry3);

        if (insertData) {
            toastMessege("Successfully inserted");
        } else
            toastMessege("Unable to insert\nPls Re-check the data again");
    }


    public void toastMessege(String messege) {
        Toast.makeText(this, messege, Toast.LENGTH_SHORT).show();
    }
}
