package br.com.alura.notepad.ui.adapter.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.alura.notepad.R;
import br.com.alura.notepad.model.Note;

public class NoteListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Note> noteList;

    public NoteListAdapter(Context mContext, List<Note> noteList) {
        this.mContext = mContext;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_note_list, parent, false);
        return new NoteListViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Note note = noteList.get(position);

        TextView noteTitle = holder.itemView.findViewById(R.id.item_note_list_title);
        noteTitle.setText(note.getTitle());

        TextView noteDescription = holder.itemView.findViewById(R.id.item_note_list_description);
        noteDescription.setText(note.getDescription());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteListViewHolder extends RecyclerView.ViewHolder {

        NoteListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
