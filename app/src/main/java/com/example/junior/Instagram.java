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

    public class Instagram extends AppCompatActivity {
        static ArrayList<String> notes2 = new ArrayList<>();
        static ArrayAdapter arrayAdapter2;
        private ImageButton imageButton;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_instagram);

            ListView listView = (ListView) findViewById(R.id.listView22);

            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example2.junior", Context.MODE_PRIVATE);

            HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);

            if (set != null ){
                notes2 = new ArrayList(set);
            }
            arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes2);
            listView.setAdapter(arrayAdapter2);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l) {
                    int ii = i;
                    Intent intent = new Intent(getApplicationContext(), instagramEditor.class);
                    intent.putExtra("noteId", ii);
                    startActivity(intent);
                }
            });

            imageButton = (ImageButton) findViewById(R.id.addNote22);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(imageButton.getId()==R.id.addNote22)

                    {
                        Intent intent = new Intent(getApplicationContext(), instagramEditor.class);
                        startActivity(intent);

                    }
                }
            });

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                    int ie = i;
                    new AlertDialog.Builder(Instagram.this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Are you sure ?")
                            .setMessage("Do you want to delete this note ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            notes2.remove(ie);
                                            arrayAdapter2.notifyDataSetChanged();

             SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example2.junior", Context.MODE_PRIVATE);

                                            HashSet<String> set = new HashSet(Instagram.notes2);

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