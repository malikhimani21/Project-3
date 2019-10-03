package com.example.quotesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class SplashActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    private TextView welcome, quotesApp;
    Typeface typeface, typeface1;
    private ImageView imageViewAnimation;
    Animation animation;
    private SharedPreferenceConfig preferenceConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ringtone);
        mediaPlayer.start();

        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        if (preferenceConfig.readLoginStatus()) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            mediaPlayer.stop();
            finish();
        }

        welcome = findViewById(R.id.textView_welome);
        quotesApp = findViewById(R.id.textView_quotesApp);

        imageViewAnimation = (ImageView) findViewById(R.id.imageViewBottomToTop);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_bottom_to_top);
        imageViewAnimation.setVisibility(View.VISIBLE);
        imageViewAnimation.startAnimation(animation);

        typeface = Typeface.createFromAsset(getAssets(), "fonts/font1.otf");
        welcome.setTypeface(typeface);

        typeface1 = Typeface.createFromAsset(getAssets(), "fonts/font2.otf");
        quotesApp.setTypeface(typeface1);


        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashActivity.this, IntroScreen.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
        mediaPlayer.stop();
    }
}
