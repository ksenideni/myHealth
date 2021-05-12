package com.example.myhealth;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.myhealth.async.DeleteAsyncTask;
import com.example.myhealth.async.InsertAsyncTask;
import com.example.myhealth.async.UpdareAsyncTask;

import java.util.List;

public class NoteRepository {

    private AppDataBase appDataBase;

    public NoteRepository(Context context) {
        appDataBase = AppDataBase.geAppdatabase(context);
    }

    public void insertNoteTask(Note note){
        new InsertAsyncTask(appDataBase.getNoteDAO()).execute(note);
    }

    public void updateNoteTask(Note note){
        new UpdareAsyncTask(appDataBase.getNoteDAO()).execute(note);
    }

    public LiveData<List<Note>> retrieveNotesTask() {
        return appDataBase.getNoteDAO().getNotes();
    }

    public void deleteNoteTask(Note note){
        new DeleteAsyncTask(appDataBase.getNoteDAO()).execute(note);
    }
}