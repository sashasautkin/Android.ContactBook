package com.example.contactbook;

import android.app.Application;

import androidx.room.Room;

import com.example.contactbook.data.AppDatabase;
import com.example.contactbook.data.noteDao;

public class App extends Application {

    private  static App instance;
    public static App getInstance(){
        return instance;

    }
    private AppDatabase database;
    private noteDao NoteDao;
    @Override
    public void onCreate() {

        super.onCreate();
        instance =this;

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-db-name").allowMainThreadQueries()
                .build();
        NoteDao = database.noteDao();
    }
    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public noteDao getNoteDao() {
        return NoteDao;
    }

    public void setNoteDao(noteDao noteDao) {
        this.NoteDao = noteDao;
    }
}
