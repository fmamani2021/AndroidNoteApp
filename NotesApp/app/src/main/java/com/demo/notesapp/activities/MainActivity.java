package com.demo.notesapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.demo.notesapp.adapters.NotesAdapter;
import com.demo.notesapp.common.Constants;
import com.demo.notesapp.common.NoteApplication;
import com.demo.notesapp.interfaces.OnNoteChecked;
import com.demo.notesapp.models.Note;
import com.demo.notesapp.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listNotes;
    private NotesAdapter adapterListVendors;
    private NoteApplication noteApplication;
    private Button btn_add_note;
    private Button btn_delete_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteApplication = (NoteApplication)getApplicationContext();


        //List<Note> notes = new ArrayList<>();
        noteApplication.setNote(new Note("Nota 1", "Comenatario 1"));
        noteApplication.setNote(new Note("Nota 2", "Comenatario 2"));
        noteApplication.setNote(new Note("Nota 3", "Comenatario 3"));


        List<Note> notes = noteApplication.getNotes();

        listNotes = findViewById(R.id.list_notes);
        btn_add_note = findViewById(R.id.btn_add_note);
        btn_delete_notes = findViewById(R.id.btn_delete_notes);

        OnNoteChecked onNoteChecked = new OnNoteChecked() {
            @Override
            public void anyChecked(boolean anyChecked) {
                if(anyChecked){
                    btn_delete_notes.setVisibility(View.VISIBLE);
                } else {
                    btn_delete_notes.setVisibility(View.GONE);
                }
            }
        };

        adapterListVendors = new NotesAdapter(this.getApplicationContext(), onNoteChecked, notes);
        listNotes.setAdapter(adapterListVendors);
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);

                Note note = (Note) listNotes.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "Edit Note with title: "+ note.getTitle(), Toast.LENGTH_SHORT).show();

                intent.putExtra(Constants.NOTE_ID, note.getId());
                startActivity(intent);
            }
        });

        btn_add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NoteActivity.class));
            }
        });

        btn_delete_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Eliminar nota");
                builder.setMessage("Seguro que desea eliminar la notas seleccionadas?");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                noteApplication.deleteNotesChecked();
                                adapterListVendors.setListNotes(noteApplication.getNotes());
                                adapterListVendors.notifyDataSetChanged();
                                dialog.cancel();
                            }
                        });

                builder.setNegativeButton(
                        "Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }

    protected void onResume() {
        super.onResume();
        adapterListVendors.setListNotes(noteApplication.getNotes());
        adapterListVendors.notifyDataSetChanged();
    }
}