package com.example.junior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class partenariatEditor extends AppCompatActivity {
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partenariat_editor);

        EditText editText = (EditText) findViewById(R.id.editTextPart);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if(noteId != -1){

            editText.setText(Partenariet.notesPart.get(noteId));

        }else {
            Partenariet.notesPart.add("Empty Note!");
            noteId = Partenariet.notesPart.size()-1;
            Partenariet.arrayAdapterPart.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Partenariet.notesPart.set(noteId, String.valueOf(charSequence));
                Partenariet.arrayAdapterPart.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.partenariat.junior", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(Partenariet.notesPart);

                sharedPreferences.edit().putStringSet("notes", set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}