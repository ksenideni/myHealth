package com.example.myhealth;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {
    @Insert
    public void Insert(Note... note);

    @Update
    public void Update(Note... note);

    @Delete
    public void Delete(Note note);

    @Query("Select * from note_table")
    public List<Note> getAllNotes();
}
