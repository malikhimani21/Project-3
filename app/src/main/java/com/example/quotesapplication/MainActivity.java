package com.example.quotesapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //    private Button logout;
    private SharedPreferenceConfig preferenceConfig;
    private Toolbar toolbar;
    private ViewFlipper viewFlipper;
    private ListView listView;
    ArrayAdapter arrayAdapter;


    String[] classesNames = {"ViewAllQuote", "Inspiration", "Success", "Available Soon", "Available Soon", "Available Soon", "Available Soon", "Available Soon", "Available Soon","Available Soon"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        logout = (Button) findViewById(R.id.logout);

        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        listView = findViewById(R.id.listView_mainActivity);
//        arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,classesNames);
        arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.menu_listitem_layout, classesNames);

        listView.setAdapter(arrayAdapter);

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                preferenceConfig.writeLoginStatus(false);
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                finish();
//            }
//        });

        viewFlipper = findViewById(R.id.flipperView_main);
        viewFlipper.setOnClickListener(this);
        viewFlipper.setFlipInterval(1500);
        viewFlipper.startFlipping();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = classesNames[position];
                try {
                    Class clsObjs = Class.forName("com.example.quotesapplication.View." + data);
                    Intent intent = new Intent(MainActivity.this, clsObjs);
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout_main_activity) {
            preferenceConfig.writeLoginStatus(false);
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        viewFlipper.showNext();
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
}
