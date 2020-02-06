package br.com.alura.notepad.ui.recyclerview.helper.callback;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import br.com.alura.notepad.dao.NoteDAO;
import br.com.alura.notepad.ui.recyclerview.adapter.NoteListAdapter;

public class NoteItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final NoteListAdapter adapter;

    public NoteItemTouchHelperCallback(NoteListAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(0, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int swipedNotePosition = viewHolder.getAdapterPosition();
        new NoteDAO().remove(swipedNotePosition);
        adapter.removeNote(swipedNotePosition);
    }
}
