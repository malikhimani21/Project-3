package com.example.quotesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PassDataViewAllQuotes extends AppCompatActivity {

    public static final String KEY1 = "com.example.quotesapplication.View.KEY1";
    TextView textView;
    Button buttonShare, smsButton;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_data_view_all_quotes);

        textView = (TextView) findViewById(R.id.textView_passData);
        buttonShare = (Button) findViewById(R.id.shareButton);
        smsButton = (Button) findViewById(R.id.smsButton);

        s = getIntent().getStringExtra(KEY1);

        textView.setText(s);

        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Select App"));
            }
        });

        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PassDataViewAllQuotes.this, SMSActivity.class);
                intent1.putExtra("SMSKEY",s);
                startActivity(intent1);
            }
        });

    }
}
