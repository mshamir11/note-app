package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Note {

    @NonNull
    @ColumnInfo(name = "text")
    public String mtext;

    @PrimaryKey(autoGenerate = true)
    public int id=0;


    public Note(){};

    public Note(String text){
        this.mtext=text;
    }


    public String getText(){
        return this.mtext;
    }





}
