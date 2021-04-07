package com.example.junior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Pmenu extends AppCompatActivity implements View.OnClickListener  {
    private ImageButton imageButton,imageButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmenu);
        
        imageButton = (ImageButton) findViewById(R.id.web);
        imageButton2 = (ImageButton) findViewById(R.id.mobile);

        imageButton.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.web:
                Intent intent = new Intent(this, ApplicationWeb.class);
                startActivity(intent);
                break;
            case R.id.mobile:
                Intent intent2 = new Intent(this, ApplicationMobile.class);
                startActivity(intent2);
                break;
        }
    }
}