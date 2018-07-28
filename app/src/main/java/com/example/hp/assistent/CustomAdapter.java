package com.example.hp.assistent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HP on 18-07-2018.
 */

public class CustomAdapter extends BaseAdapter {

    private ArrayList<Todo> data;
    private ClickCallback callback;

    public CustomAdapter(ArrayList<Todo> listData) {
        data = listData;
    }

    public CustomAdapter(ArrayList<Todo> data, ClickCallback callback)
    {
        this.data = data;
        this.callback = callback;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Todo getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.disp, parent,false);
        }

        TextView sub_disp = (TextView) convertView.findViewById(R.id.subjet_disp);
        TextView date_disp = (TextView) convertView.findViewById(R.id.date_disp);
        TextView desc_disp = (TextView) convertView.findViewById(R.id.description_disp);
        TextView id_disp = (TextView) convertView.findViewById(R.id.id_disp);

        final Todo todo = getItem(position);

        sub_disp.setText(todo.getDisp1());
        date_disp.setText(todo.getDisp2());
        desc_disp.setText(todo.getDisp3());
        id_disp.setText(todo.getId());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onItemClick(position, todo);
                }
            }
        });

        return convertView;
    }
}
