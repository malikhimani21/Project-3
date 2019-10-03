package com.example.quotesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    private EditText username, password;
    private Button login_button;
    int counts = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        username = (EditText) findViewById(R.id.admin_user_name);
        password = (EditText) findViewById(R.id.admin_password);
        login_button = (Button) findViewById(R.id.admin_login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String USER = username.getText().toString();
                String PASS = password.getText().toString();

                if (TextUtils.isEmpty(USER) && TextUtils.isEmpty(PASS)) {
                    Toast.makeText(getApplicationContext(), "Value can't be empty...", Toast.LENGTH_SHORT).show();
                } else {
                    if(USER.equals("m") && PASS.equals("1")){
                        Intent intent = new Intent(AdminLogin.this, InsertQuotesActivity.class);
                        startActivity(intent);
                    } else {
                        counts--;
                        Toast.makeText(getApplicationContext(),"Wrong inclusion... Attempt Left" + String.valueOf(counts),Toast.LENGTH_SHORT).show();
                        if(counts==0){
                            login_button.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(),"Restart App Again",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
