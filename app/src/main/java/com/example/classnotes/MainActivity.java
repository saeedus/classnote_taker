package com.example.classnotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView notesList;
    ArrayList<String> noteTitle = new ArrayList<>();
    ArrayList<String> noteDate = new ArrayList<>();
    ArrayList<String> noteDetails = new ArrayList<>();

    FloatingActionButton newNoteBtn;
    DBHandler dbHandler;
    ArrayList<NoteModal> savedNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesList = (ListView) findViewById(R.id.notesList);
        newNoteBtn = findViewById(R.id.floating_action_btn);

        dbHandler = new DBHandler(MainActivity.this);
        readFromDB();

        newNoteBtn.setOnClickListener(view -> createNewNote());
        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
                intent.putExtra("title", noteTitle.get(position));
                intent.putExtra("note", noteDetails.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        noteTitle.clear();
        noteDate.clear();
        noteDetails.clear();
        readFromDB();
    }

    private void createNewNote() {
        Intent intent = new Intent(this, NewNoteActivity.class);
        startActivity(intent);
    }

    private void readFromDB() {
        try {
            savedNotes = dbHandler.readNotes();
            for (int i = 0; i < savedNotes.size(); i++) {
                noteTitle.add(i, savedNotes.get(i).getTitle());
                noteDetails.add(i, savedNotes.get(i).getNoteDetails());
                noteDate.add(i, savedNotes.get(i).getDate());
            }
            CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), noteTitle, noteDate, noteDetails);
            notesList.setAdapter(customAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}