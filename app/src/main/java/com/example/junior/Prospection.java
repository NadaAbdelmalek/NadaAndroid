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

public class Prospection extends AppCompatActivity {
    static ArrayList<String> notesPros = new ArrayList<>();
    static ArrayAdapter arrayAdapterPros;
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospection);



        ListView listView = (ListView) findViewById(R.id.listViewPros);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.prospection.junior", Context.MODE_PRIVATE);

        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);

        if (set != null ){
            notesPros = new ArrayList(set);
        }
        arrayAdapterPros = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notesPros);
        listView.setAdapter(arrayAdapterPros);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l) {
                int ii = i;
                Intent intent = new Intent(getApplicationContext(), ProspectionEditor.class);
                intent.putExtra("noteId", ii);
                startActivity(intent);
            }
        });

        imageButton = (ImageButton) findViewById(R.id.addNotePros);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageButton.getId()==R.id.addNotePros)

                {
                    Intent intent = new Intent(getApplicationContext(), ProspectionEditor.class);
                    startActivity(intent);

                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                int ie = i;
                new AlertDialog.Builder(Prospection.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this note ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        notesPros.remove(ie);
                                        arrayAdapterPros.notifyDataSetChanged();

                                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.prospection.junior", Context.MODE_PRIVATE);

                                        HashSet<String> set = new HashSet(Prospection.notesPros);

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