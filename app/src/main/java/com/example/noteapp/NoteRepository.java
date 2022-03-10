package com.example.noteapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;


    NoteRepository(NoteDao noteDao) {
        mNoteDao=noteDao;
        mAllNotes=noteDao.getAllNotes();

    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Note note) {

        NoteRoomDatabase.databaseWriteExecutor.execute(() -> mNoteDao.insert(note));
    }

    void delete(Note note){
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> mNoteDao.delete(note));
    }




}
