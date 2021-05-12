package com.example.myhealth.async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.myhealth.Note;
import com.example.myhealth.NoteDAO;

public class UpdareAsyncTask extends AsyncTask<Note, Void, Void> {

    private static final String TAG = "InsertAsyncTask";
    private NoteDAO noteDAO;

    public UpdareAsyncTask(NoteDAO dao) {
        noteDAO=dao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        Log.d(TAG, "doInBackground: thread: " + Thread.currentThread().getName());
        noteDAO.UpdateNotes(notes);
        return null;
    }
}
