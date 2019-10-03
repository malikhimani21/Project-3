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

public class Success extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    private Query query1;
    List<Insert_ModelClass> list;
    public static final String KEY1 = "com.example.quotesapplication.View.KEY1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity_success);

        listView = (ListView) findViewById(R.id.listview_success1);
        query1 = FirebaseDatabase.getInstance().getReference("quotes").orderByChild("category").equalTo("Success");
        list = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    Insert_ModelClass userModelClass = snap.getValue(Insert_ModelClass.class);

                    list.add(userModelClass);
                }
                SuccessAdapterClass successAdapterClass = new SuccessAdapterClass(Success.this, list);
                listView.setAdapter(successAdapterClass);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Insert_ModelClass insert_modelClass = list.get(position);
                Intent intent = new Intent(Success.this, PassDataViewAllQuotes.class);
                intent.putExtra(KEY1,insert_modelClass.getQuotes());
                startActivity(intent);
            }
        });

    }

}
