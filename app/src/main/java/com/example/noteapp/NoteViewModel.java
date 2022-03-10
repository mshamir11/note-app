package com.example.noteapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mNoteRepository;
    private final LiveData<List<Note>> mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);


        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        NoteDao mNoteDao = db.getNoteDao();
        mNoteRepository=new NoteRepository(mNoteDao);
        mAllNotes = mNoteRepository.getAllNotes();

    }
    public LiveData<List<Note>>  getmAllNotes() { return this.mAllNotes; }
    public void insert(Note note) { this.mNoteRepository.insert(note); }

    public void delete(Note note){
        this.mNoteRepository.delete(note);
    }
}
