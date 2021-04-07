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

public class ProspectionEditor extends AppCompatActivity {
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospection_editor);



        EditText editText = (EditText) findViewById(R.id.editTextPros);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if(noteId != -1){

            editText.setText(Prospection.notesPros.get(noteId));

        }else {
            Prospection.notesPros.add("Empty Note!");
            noteId = Prospection.notesPros.size()-1;
            Prospection.arrayAdapterPros.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Prospection.notesPros.set(noteId, String.valueOf(charSequence));
                Prospection.arrayAdapterPros.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.prospection.junior", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(Prospection.notesPros);

                sharedPreferences.edit().putStringSet("notes", set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}