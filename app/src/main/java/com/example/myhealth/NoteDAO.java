package com.example.myhealth;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {
    @Insert
    long[] InsertNotes(Note... notes);

    @Update
    int UpdateNotes(Note... notes);

    @Delete
    int DeleteNotes(Note... notes);

    @Query("Select * from note_table")
    LiveData<List<Note>> getNotes();
}
