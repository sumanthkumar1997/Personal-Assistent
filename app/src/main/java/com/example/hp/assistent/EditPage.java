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
import android.widget.TextView;
import android.widget.Toast;

public class EditPage extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    String mymonth,d;
    String myDate;
    int i=0;
    String eid,esub,edate,edesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);

        final EditText subject = (EditText) findViewById(R.id.subject);
        final CalendarView date = (CalendarView) findViewById(R.id.calender);
        final EditText desc = (EditText) findViewById(R.id.description);
        Button save = (Button) findViewById(R.id.save);
        Button back=(Button) findViewById(R.id.back);
        TextView mdate=(TextView) findViewById(R.id.date);

        eid= getIntent().getExtras().getString("id");
        esub=getIntent().getExtras().getString("sub");
        edate=getIntent().getExtras().getString("date");
        edesc=getIntent().getExtras().getString("desc");
        mdate.setText("Previous date"+edate);

        subject.setText(esub);
        myDate=edate;
        desc.setText(edesc);

        //Below code works only for API level above 24
      /*  String parts[]=edate.split("-");
        String year=parts[0];
        String month=parts[1];
        String day=parts[2];

        String newDate=day+"/"+month+"/"+year;
        date.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(newDate).getTime(),true,true);
        */

        mDatabaseHelper = new DatabaseHelper(this);

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

                if(entry1.length()==0)
                {
                    toastMessege("Subject can't be empty");
                }
                else if((entry1.length() != 0) && (entry2.length() != 0)) {
                    modify(eid,entry1, entry2, entry3);

                    Intent i = new Intent(EditPage.this, EditList.class);
                    startActivity(i);
                    finish();
                }


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditPage.this,EditList.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void modify(String entry0,String entry1, String entry2, String entry3) {
         mDatabaseHelper.update(entry0,entry1, entry2, entry3);

        /*if (insertData) {
            toastMessege("successfully inserted");
        } else
            toastMessege("Unable to insert");*/
    }


    public void toastMessege(String messege) {
        Toast.makeText(this, messege, Toast.LENGTH_SHORT).show();
    }
}
