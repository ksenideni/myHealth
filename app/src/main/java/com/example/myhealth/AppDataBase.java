package com.example.myhealth;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

//синглтон, не будет нескольких экземпляров бд
@Database(entities = {Reminders.class, Note.class},version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase INSTANCE = null;

    public abstract RoomDAO getRoomDAO();
    public abstract NoteDAO getNoteDAO();

    //users - имя бд
    public static AppDataBase geAppdatabase(Context context){

        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"users")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
