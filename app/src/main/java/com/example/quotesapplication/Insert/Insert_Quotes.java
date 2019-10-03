package com.example.quotesapplication.Insert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quotesapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Insert_Quotes extends AppCompatActivity {

    private Spinner spinner;
    private EditText editTextQuote, editTextQuoteCatId;
    private Button button;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_quotes_activity);

        spinner = findViewById(R.id.insert_quote_spinner);
        editTextQuote = findViewById(R.id.insert_quotes_edittext);
        editTextQuoteCatId = findViewById(R.id.insert_quotes_category_id);
        button = findViewById(R.id.insert_quote_button);

        databaseReference = FirebaseDatabase.getInstance().getReference("quotes");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String CATEGORY = spinner.getSelectedItem().toString();
                String CATEGORY_ID = editTextQuoteCatId.getText().toString();
                String QUOTE = editTextQuote.getText().toString();

                if (!TextUtils.isEmpty(CATEGORY_ID) && !TextUtils.isEmpty(QUOTE)) {

                    String unique_ids = databaseReference.push().getKey();
                    Insert_ModelClass insert_modelClass = new Insert_ModelClass(unique_ids, CATEGORY, CATEGORY_ID, QUOTE);
                    databaseReference.child(unique_ids).setValue(insert_modelClass);
                    Toast.makeText(getApplicationContext(), "Quote Added", Toast.LENGTH_SHORT).show();
                    editTextQuoteCatId.setText("");
                    editTextQuote.setText("");

                } else {
                    Toast.makeText(getApplicationContext(), "Insert value first", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
