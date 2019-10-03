package com.example.quotesapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private ImageView imageView;
    private SharedPreferenceConfig preferenceConfig;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        imageView = (ImageView) findViewById(R.id.login_button);

        if (preferenceConfig.readLoginStatus()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String USER = username.getText().toString();
                String PASS = password.getText().toString();

                if (USER.equals(getResources().getString(R.string.username)) && PASS.equals(getResources().getString(R.string.password))) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    preferenceConfig.writeLoginStatus(true);
                    finish();
                } else {
                    counter--;
                    Toast.makeText(getApplicationContext(), "Login Failed !", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Attempt Left : " + String.valueOf(counter), Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");

                    if (counter == 0) {
                        imageView.setEnabled(false);
                        Toast.makeText(getApplicationContext(), "You have restart your app", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void replay(View view) {
        PreferenceManager preferenceManager = new PreferenceManager(getApplicationContext());
        preferenceManager.setFirstTimeLaunch(true);
        startActivity(new Intent(LoginActivity.this, IntroScreen.class));
        finish();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.login_file, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.introScreen:
//                PreferenceManager preferenceManager = new PreferenceManager(getApplicationContext());
//                preferenceManager.setFirstTimeLaunch(true);
//                startActivity(new Intent(LoginActivity.this, IntroScreen.class));
//                finish();
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void loginAsAdminMethodd(View view){
        Intent intent = new Intent(LoginActivity.this, AdminLogin.class);
        startActivity(intent);
    }
}
