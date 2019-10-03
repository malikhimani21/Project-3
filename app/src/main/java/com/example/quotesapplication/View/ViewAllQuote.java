package com.example.quotesapplication.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.quotesapplication.Insert.Insert_ModelClass;
import com.example.quotesapplication.PassDataViewAllQuotes;
import com.example.quotesapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewAllQuote extends AppCompatActivity {

    public static final String KEY1 = "com.example.quotesapplication.View.KEY1";

    private ListView listView;
    DatabaseReference databaseReference;
    List<Insert_ModelClass> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_quote);

        listView = (ListView) findViewById(R.id.activity_view_all_qListView);

        // databaseReference = FirebaseDatabase.getInstance().getReference("quotes").child("Inspiration");

        list = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //   Query query = FirebaseDatabase.getInstance().getReference("quotes").orderByChild("category").equalTo("Inspiration");
        Query query = FirebaseDatabase.getInstance().getReference("quotes");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    Insert_ModelClass insert_modelClass = snap.getValue(Insert_ModelClass.class);

                    list.add(insert_modelClass);
                }
                ViewAllAdapterClass viewAllAdapterClass = new ViewAllAdapterClass(ViewAllQuote.this, list);
                listView.setAdapter(viewAllAdapterClass);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Insert_ModelClass insert_modelClass = list.get(position);
                Intent intent = new Intent(ViewAllQuote.this, PassDataViewAllQuotes.class);
                intent.putExtra(KEY1,insert_modelClass.getQuotes());
                startActivity(intent);
            }
        });


    }
}
