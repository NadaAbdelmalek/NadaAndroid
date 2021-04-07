package com.example.junior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DevMenu extends AppCompatActivity implements View.OnClickListener{
    private ImageButton imageButton,imageButton2,imageButton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_menu);

        imageButton = (ImageButton) findViewById(R.id.pros);
        imageButton2 = (ImageButton) findViewById(R.id.part);

        imageButton.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.pros:
                Intent intent = new Intent(this, Prospection.class);
                startActivity(intent);
                break;
            case R.id.part:
                Intent intent2 = new Intent(this, Partenariet.class);
                startActivity(intent2);
                break;
        }
    }
}