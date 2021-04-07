package com.example.junior;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class ApplicationWeb extends AppCompatActivity {
    static ArrayList<String> notesWeb = new ArrayList<>();
    static ArrayAdapter arrayAdapterWeb;
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_web);

        ListView listView = (ListView) findViewById(R.id.listViewWeb);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.web.junior", Context.MODE_PRIVATE);

        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);

        if (set != null ){
            notesWeb = new ArrayList(set);
        }
        arrayAdapterWeb = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notesWeb);
        listView.setAdapter(arrayAdapterWeb);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l) {
                int ii = i;
                Intent intent = new Intent(getApplicationContext(), applicationWebEditor.class);
                intent.putExtra("noteId", ii);
                startActivity(intent);
            }
        });

        imageButton = (ImageButton) findViewById(R.id.addNoteWeb);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageButton.getId()==R.id.addNoteWeb)

                {
                    Intent intent = new Intent(getApplicationContext(), applicationWebEditor.class);
                    startActivity(intent);

                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                int ie = i;
                new AlertDialog.Builder(ApplicationWeb.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this note ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        notesWeb.remove(ie);
                                        arrayAdapterWeb.notifyDataSetChanged();

                                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.web.junior", Context.MODE_PRIVATE);

                                        HashSet<String> set = new HashSet(ApplicationWeb.notesWeb);

                                        sharedPreferences.edit().putStringSet("notes", set).apply();
                                    }
                                }
                        )
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });
    }
}