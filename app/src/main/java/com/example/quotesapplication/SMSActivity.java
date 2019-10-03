package com.example.quotesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMSActivity extends AppCompatActivity {

    private EditText editTextMobile, editTextMessage;
    private Button sendsms;
    String smsQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        editTextMobile = (EditText) findViewById(R.id.sms_editTextNumber);
        editTextMessage = (EditText) findViewById(R.id.sms_editTextMessage);
        sendsms = (Button) findViewById(R.id.sms_buttonSendSMS);

        smsQ = getIntent().getStringExtra("SMSKEY");
        editTextMessage.setText(smsQ);

        sendsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NUMBER = editTextMobile.getText().toString();
                String MESSAGE = editTextMessage.getText().toString();

                Intent intent = new Intent(getApplicationContext(), SMSActivity.class);

                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(NUMBER, null, MESSAGE, pendingIntent, null);

                Toast.makeText(getApplicationContext(), "Message Sent successfully!", Toast.LENGTH_LONG).show();

                editTextMessage.setText("");
                editTextMobile.setText("");
            }
        });


    }
}
