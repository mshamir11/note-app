package com.example.noteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesRVAdapter extends RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder> {

    private ArrayList<Note> allNotes;
    private Context context;
    private INotesRVAdapter listener;

    public class NoteViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView deleteButton;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
            deleteButton=itemView.findViewById(R.id.delete_button);


        }
    }




    public NotesRVAdapter(Context context,INotesRVAdapter listener){
        this.context= context;
        this.listener=listener;
        allNotes=new ArrayList<Note>();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
        NoteViewHolder viewHolder= new NoteViewHolder(view);

        viewHolder.deleteButton.setOnClickListener((View.OnClickListener)(it->{

            INotesRVAdapter item = NotesRVAdapter.this.listener;
            Object sample_string = NotesRVAdapter.this.allNotes.get(viewHolder.getAdapterPosition());
            item.onItemClicked((Note)sample_string);
        }) );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note curr_note = allNotes.get(position);
        holder.textView.setText(curr_note.getText());
    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public void update(List<Note> notes){
        allNotes.clear();
        allNotes.addAll(notes);
        notifyDataSetChanged();
    }
}


interface INotesRVAdapter{
    void onItemClicked(Note note);
}