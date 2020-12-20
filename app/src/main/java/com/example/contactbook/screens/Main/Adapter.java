package com.example.contactbook.screens.Main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.contactbook.App;
import com.example.contactbook.R;
import com.example.contactbook.model.Note;
import com.example.contactbook.screens.details.NoteDetailsActivity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.NoteViewHolder> {

    private SortedList<Note> sortedList;
    public Adapter(){

        sortedList = new SortedList<>(Note.class, new SortedList.Callback<Note>() {
            @Override
            public int compare(Note o1, Note o2) {

                return (int) (o2.timestamp - o1.timestamp);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position,count);

            }

            @Override
            public boolean areContentsTheSame(Note oldItem, Note newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Note item1, Note item2) {
                return item1.uid == item2.uid;
            }

            @Override
            public void onInserted(int position, int count) {
               notifyItemRangeInserted(position,count);

            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position,count);

            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition,toPosition);

            }
        });
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_list,parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItems(List<Note> notes){
         sortedList.replaceAll(notes);
    }

    static  class NoteViewHolder extends  RecyclerView.ViewHolder{
       TextView noteText;
       TextView noteNumber;
       View delete;

       Note note;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteText = itemView.findViewById(R.id.note_text);
            noteNumber = itemView.findViewById(R.id.note_number);
            delete = itemView.findViewById(R.id.delete);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NoteDetailsActivity.start((Activity) itemView.getContext(), note);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    App.getInstance().getNoteDao().delete(note);
                }
            });

        }



        public  void bind(Note note){
            this.note=note;
            noteText.setText(note.text);
            noteNumber.setText(note.phone_number);

        }


    }
}
