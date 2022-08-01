package com.demo.notesapp.common;

import android.app.Application;
import android.content.res.Configuration;

import com.demo.notesapp.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteApplication extends Application {
    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    @Override
    public void onCreate() {
        super.onCreate();
        // Required initialization logic here!
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }




    private List<Note> notes = new ArrayList<>();

    public List<Note> getNotes() {
        return notes;
    }

    public void setNote(Note note) {
        if(note != null){
            if(notes == null)
                notes = new ArrayList<>();
            notes.add(note);
        }
    }

    public Note getNoteById(String noteId) {
        //return notes.stream().filter(p -> p.getId() == noteId).findFirst().orElse(null);
        Note noteResponse = null;
        for (Note note: notes) {
            if(note.getId().equals(noteId)){
                noteResponse = note;
            }
        }
        return noteResponse;
    }

    public void UpdateNote(Note noteToUpdate) {
        for (Note note: notes) {
            if(note.getId().equals(noteToUpdate.getId())){
                note.setTitle(noteToUpdate.getTitle());
                note.setComment(noteToUpdate.getComment());
            }
        }
    }

    public void deleteNotesChecked() {
        List<Note> listToRemove = new ArrayList<>();
        for (Note note: notes) {
            if(note.getChecked()){
                listToRemove.add(note);
            }
        }

        for (Note note: listToRemove) {
            if(note.getChecked()){
                notes.remove(note);
            }
        }
    }
}
