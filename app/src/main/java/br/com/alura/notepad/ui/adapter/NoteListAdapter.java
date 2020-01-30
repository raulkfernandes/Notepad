package br.com.alura.notepad.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.alura.notepad.R;
import br.com.alura.notepad.model.Note;

public class NoteListAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Note> noteList;

    public NoteListAdapter(Context mContext, List<Note> noteList) {
        this.mContext = mContext;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Note getItem(int index) {
        return noteList.get(index);
    }

    @Override
    public long getItemId(int index) {
        return 0;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {

        View mView = view;

        if (mView == null) {
            mView = LayoutInflater.from(mContext).inflate(R.layout.item_note_list, viewGroup, false);
        }

        Note note = noteList.get(index);
        showTitle(mView, note);
        showDescription(mView, note);

        return mView;
    }

    private void showDescription(View mView, Note note) {
        TextView description = mView.findViewById(R.id.item_note_list_description);
        description.setText(note.getDescription());
    }

    private void showTitle(View mView, Note note) {
        TextView title = mView.findViewById(R.id.item_note_list_title);
        title.setText(note.getTitle());
    }
}
