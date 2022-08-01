package com.demo.notesapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.demo.notesapp.common.Constants;
import com.demo.notesapp.common.NoteApplication;
import com.demo.notesapp.models.Note;
import com.demo.notesapp.R;

public class NoteActivity extends AppCompatActivity {

    private EditText edt_title;
    private EditText edt_comment;
    private Button btn_save_note;
    private NoteApplication noteApplication;
    private boolean isNewNote;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteApplication = (NoteApplication)getApplicationContext();

        edt_title = findViewById(R.id.edt_title);
        edt_comment = findViewById(R.id.edt_comment);
        btn_save_note = findViewById(R.id.btn_save_note);

        btn_save_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNewNote){
                    noteApplication.setNote(
                            new Note(edt_title.getText().toString(), edt_comment.getText().toString())
                    );
                } else {
                    note.setTitle(edt_title.getText().toString());
                    note.setComment(edt_comment.getText().toString());
                    noteApplication.UpdateNote(note);
                }
                finish();
            }
        });

        Intent intent = getIntent();
        String noteId = intent.getStringExtra(Constants.NOTE_ID);
        isNewNote = noteId == Constants.NOTE_ID_NOT_SET;
        if(!isNewNote) {
            note = noteApplication.getNoteById(noteId);
            edt_title.setText(note.getTitle());
            edt_comment.setText(note.getComment());
        }
    }
}