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
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int noteInitialPosition = viewHolder.getAdapterPosition();
        int noteFinalPosition = target.getAdapterPosition();
        new NoteDAO().swap(noteInitialPosition, noteFinalPosition);
        adapter.swap(noteInitialPosition, noteFinalPosition);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int notePosition = viewHolder.getAdapterPosition();
        new NoteDAO().remove(notePosition);
        adapter.removeNote(notePosition);
    }
}
