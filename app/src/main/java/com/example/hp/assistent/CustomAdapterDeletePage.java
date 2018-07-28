package com.example.hp.assistent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HP on 24-07-2018.
 */
public class CustomAdapterDeletePage extends BaseAdapter {

    private ArrayList<Todo> data;
    private ClickCallback callback;

    public CustomAdapterDeletePage(ArrayList<Todo> listData) {
        data = listData;
    }

    public CustomAdapterDeletePage(ArrayList<Todo> data, ClickCallback callback) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.deletedisp, parent, false);
        }

        TextView sub_disp = (TextView) convertView.findViewById(R.id.subjet_disp_dele);
        TextView date_disp = (TextView) convertView.findViewById(R.id.date_disp_dele);
        TextView desc_disp = (TextView) convertView.findViewById(R.id.description_disp_dele);
        TextView id_disp = (TextView) convertView.findViewById(R.id.id_disp_dele);
        Button dele=(Button) convertView.findViewById(R.id.btn_delete);

        final Todo todo = getItem(position);

        sub_disp.setText(todo.getDisp1());
        date_disp.setText(todo.getDisp2());
        desc_disp.setText(todo.getDisp3());
        id_disp.setText(todo.getId());

        dele.setOnClickListener(new View.OnClickListener() {
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
