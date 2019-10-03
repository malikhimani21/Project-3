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

public class SuccessAdapterClass extends ArrayAdapter<Insert_ModelClass> {

    Activity activity;
    List<Insert_ModelClass> list;

    public SuccessAdapterClass(Activity activity, List<Insert_ModelClass> list) {
        super(activity, R.layout.layout_success, list);
        this.activity = activity;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_success, null, true);

        TextView quote = (TextView) view.findViewById(R.id.success_all_quote_txt);

        Insert_ModelClass insert_modelClass = list.get(position);

        quote.setText(insert_modelClass.getQuotes());

        return view;
    }

}
