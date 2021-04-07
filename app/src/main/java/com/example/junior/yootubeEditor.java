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

public class yootubeEditor extends AppCompatActivity {
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yootube_editor);


        EditText editText = (EditText) findViewById(R.id.editText);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

            if(noteId != -1){

                editText.setText(Yootube.notes.get(noteId));

            }else {
                Yootube.notes.add("Empty Note!");
                noteId = Yootube.notes.size()-1;
                Yootube.arrayAdapter.notifyDataSetChanged();
            }

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    Yootube.notes.set(noteId, String.valueOf(charSequence));
                    Yootube.arrayAdapter.notifyDataSetChanged();

                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.junior", Context.MODE_PRIVATE);
                    HashSet<String> set = new HashSet(Yootube.notes);

                    sharedPreferences.edit().putStringSet("notes", set).apply();

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

    }
}