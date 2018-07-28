package com.example.hp.assistent;

/**
 * Created by HP on 18-07-2018.
 */

public class Todo {

    private  String subject,id;
    private  String date;
    private  String description;

    public Todo(String disp,String disp1, String disp2, String disp3) {
        this.id=disp;
        this.subject = disp1;
        this.date = disp2;
        this.description = disp3;
    }
    public String getId(){return id;}
    public String getDisp1() {
        return subject;
    }

    public String getDisp2() {
        return date;
    }

    public String getDisp3() {
        return description;
    }
}