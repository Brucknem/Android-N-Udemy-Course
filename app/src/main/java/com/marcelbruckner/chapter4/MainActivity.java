package com.marcelbruckner.chapter4;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fade(View view){
        ImageView bart = (ImageView) view;
        bart.animate().alpha(0f).setDuration(2000);

        ImageView homer = findViewById(R.id.homer);
        homer.animate().alpha(1f).setDuration(2000);
    }
}