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

public class applicationMobileEditor extends AppCompatActivity {
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_mobile_editor);

        EditText editText = (EditText) findViewById(R.id.editTextMobile);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if(noteId != -1){

            editText.setText(ApplicationMobile.notesMobile.get(noteId));

        }else {
            ApplicationMobile.notesMobile.add("Empty Note!");
            noteId = ApplicationMobile.notesMobile.size()-1;
            ApplicationMobile.arrayAdapterMobile.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                ApplicationMobile.notesMobile.set(noteId, String.valueOf(charSequence));
                ApplicationMobile.arrayAdapterMobile.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.mobile.junior", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(ApplicationMobile.notesMobile);

                sharedPreferences.edit().putStringSet("notes", set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}