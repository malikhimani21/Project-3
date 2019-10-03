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

public class ViewAllAdapterClass extends ArrayAdapter<Insert_ModelClass> {


    Activity activity;
    List<Insert_ModelClass> list;

    public ViewAllAdapterClass(Activity activity, List<Insert_ModelClass> list) {
        super(activity, R.layout.layout_view_all_quotes,list);
        this.activity = activity;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_view_all_quotes,null,true);

        TextView category = (TextView) view.findViewById(R.id.view_all_quote_quoteCategory);
        TextView quote = (TextView) view.findViewById(R.id.view_all_quote_quote);

        Insert_ModelClass insert_modelClass = list.get(position);

        category.setText(insert_modelClass.getCategory());
        quote.setText(insert_modelClass.getQuotes());

        return view;
    }


}
