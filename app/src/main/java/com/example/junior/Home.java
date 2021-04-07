package com.example.junior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home extends AppCompatActivity implements View.OnClickListener {
    private ImageButton imageButton,imageButton2,imageButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageButton = (ImageButton) findViewById(R.id.marketing);
        imageButton2 = (ImageButton) findViewById(R.id.project);
        imageButton3 = (ImageButton) findViewById(R.id.devco);

        imageButton.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.marketing:
                Intent intent = new Intent(this, Menu.class);
                startActivity(intent);
                break;
            case R.id.project:
                Intent intent2 = new Intent(this, Pmenu.class);
                startActivity(intent2);
                break;
            case R.id.devco:
                Intent intent3 = new Intent(this, DevMenu.class);
                startActivity(intent3);
                break;
        }
    }
}