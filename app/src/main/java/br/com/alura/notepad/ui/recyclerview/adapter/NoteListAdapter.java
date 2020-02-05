package br.com.alura.notepad.ui.recyclerview.adapter;

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
import br.com.alura.notepad.ui.recyclerview.adapter.listener.OnItemClickListener;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder> {

    private final Context mContext;
    private final List<Note> noteList;
    private OnItemClickListener onItemClickListener;

    public NoteListAdapter(Context mContext, List<Note> noteList) {
        this.mContext = mContext;
        this.noteList = noteList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NoteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_note_list, parent, false);
        return new NoteListViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.bindInformation(note);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteListViewHolder extends RecyclerView.ViewHolder {

        private final TextView noteTitle;
        private final TextView noteDescription;
        private Note note;

        NoteListViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.item_note_list_title);
            noteDescription = itemView.findViewById(R.id.item_note_list_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(note);
                }
            });
        }

        void bindInformation(Note note) {
            this.note = note;
            initializeFields(note);
        }

        private void initializeFields(Note note) {
            noteTitle.setText(note.getTitle());
            noteDescription.setText(note.getDescription());
        }
    }

    public void addNewNote(Note note) {
        noteList.add(note);
        notifyDataSetChanged();
    }
}
