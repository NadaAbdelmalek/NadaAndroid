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

public class applicationWebEditor extends AppCompatActivity {
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_web_editor);

        EditText editText = (EditText) findViewById(R.id.editTextWeb);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if(noteId != -1){

            editText.setText(ApplicationWeb.notesWeb.get(noteId));

        }else {
            ApplicationWeb.notesWeb.add("Empty Note!");
            noteId = ApplicationWeb.notesWeb.size()-1;
            ApplicationWeb.arrayAdapterWeb.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                ApplicationWeb.notesWeb.set(noteId, String.valueOf(charSequence));
                ApplicationWeb.arrayAdapterWeb.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.web.junior", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(ApplicationWeb.notesWeb);

                sharedPreferences.edit().putStringSet("notes", set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}