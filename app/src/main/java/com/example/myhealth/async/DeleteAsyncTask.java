package com.example.myhealth.async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.myhealth.Note;
import com.example.myhealth.NoteDAO;

public class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {

    private static final String TAG = "InsertAsyncTask";
    private NoteDAO noteDAO;

    public DeleteAsyncTask(NoteDAO dao) {
        noteDAO=dao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        Log.d(TAG, "doInBackground: thread: " + Thread.currentThread().getName());
        noteDAO.DeleteNotes(notes);
        return null;
    }
}
