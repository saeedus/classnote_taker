package com.example.classnotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;

public class NewNoteActivity extends Activity {

    private Button backButton;
    private EditText title, note;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        Intent i = getIntent();
        backButton = findViewById(R.id.backButton);
        title = findViewById(R.id.title);
        note = findViewById(R.id.description);

        if(i.getExtras() != null) {
            title.setText(i.getStringExtra("title"));
            note.setText(i.getStringExtra("note"));
        }

        dbHandler = new DBHandler(NewNoteActivity.this);

        backButton.setOnClickListener(view -> popAndSaveData());
    }

    private void popAndSaveData() {
        onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            saveToDB(LocalDate.now().toString());
        }
    }

    private void saveToDB(String date) {
        // below line is to get data from all edit text fields.
        String noteTitle = title.getText().toString();
        String noteDetails = note.getText().toString();
        String dateSaved = date;

        // validating if the text fields are empty or not.
        if (noteTitle.isEmpty() && noteDetails.isEmpty()) {
            Toast.makeText(NewNoteActivity.this, "Empty note discarded", Toast.LENGTH_SHORT).show();
            return;
        }

        // on below line we are calling a method to add new
        // course to sqlite data and pass all our values to it.
        dbHandler.addNewNote(noteTitle, noteDetails, dateSaved);

        // after adding the data we are displaying a toast message.
        Toast.makeText(NewNoteActivity.this, "Note has been saved.", Toast.LENGTH_SHORT).show();
    }
}
