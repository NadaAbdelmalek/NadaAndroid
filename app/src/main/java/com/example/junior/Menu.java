package com.example.junior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity implements View.OnClickListener{
    private ImageButton imageButton, imageButton2, imageButton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        imageButton = (ImageButton) findViewById(R.id.yootubeButton);
        imageButton2 = (ImageButton) findViewById(R.id.facebook);
        imageButton3 = (ImageButton) findViewById(R.id.instagram);
        imageButton.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.yootubeButton:
                Intent intent = new Intent(this, Yootube.class);
                startActivity(intent);
                break;
            case R.id.instagram:
                Intent intent1 = new Intent(this, Instagram.class);
                startActivity(intent1);
                break;
            case R.id.facebook:
                Intent intent2 = new Intent(this, Facebook.class);
                startActivity(intent2);
                break;
        }
    }
}