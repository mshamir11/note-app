package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements INotesRVAdapter {

    private NoteViewModel mNoteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        NotesRVAdapter adapter = new NotesRVAdapter(this,this);
        recyclerView.setAdapter(adapter);

        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        mNoteViewModel.getmAllNotes().observe(this, adapter::update);
        
    }

    @Override
    public void onItemClicked(Note note) {
        mNoteViewModel.delete(note);
        Toast.makeText(this,note.getText() + " is deleted",Toast.LENGTH_SHORT).show();
    }




    public void submitData(View view) {
        TextView input =findViewById(R.id.input_text);
        String noteText = input.getText().toString();
        if (!noteText.isEmpty()){
            mNoteViewModel.insert(new Note(noteText));
            Toast.makeText(this,noteText + " is inserted",Toast.LENGTH_SHORT).show();

        }

    }
}