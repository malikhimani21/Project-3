package com.example.quotesapplication.View;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quotesapplication.Insert.Insert_ModelClass;
import com.example.quotesapplication.R;

import java.util.List;

public class InspirationAdapterClass extends ArrayAdapter<Insert_ModelClass> {

    Activity activity;
    List<Insert_ModelClass> list;

    public InspirationAdapterClass(Activity activity, List<Insert_ModelClass> list) {
        super(activity, R.layout.layout_inspiration,list);
        this.activity = activity;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_inspiration,null,true);

        TextView quote_inspi = (TextView) view.findViewById(R.id.inspiration_view_quote_quote);

        Insert_ModelClass insert_modelClass = list.get(position);

        quote_inspi.setText(insert_modelClass.getQuotes());

        return view;
    }


}
