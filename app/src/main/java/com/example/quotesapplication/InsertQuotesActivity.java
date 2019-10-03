package com.example.quotesapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class InsertQuotesActivity extends AppCompatActivity {

    private ListView listView;
    String[] insert_class_names = {"Insert_Quotes"};
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_quotes);

        listView = (ListView) findViewById(R.id.listView_insertQuotes);
        arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,insert_class_names);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = insert_class_names[position];
                try {
                    Class clsNames1 = Class.forName("com.example.quotesapplication.Insert."+data);
                    Intent intent = new Intent(InsertQuotesActivity.this, clsNames1);
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(InsertQuotesActivity.this);
        builder.setTitle("Alert for Confirmation");
        builder.setMessage("Do you want to close the App ?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You're in App ! Have a good day...", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
