package br.com.alura.notepad.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import br.com.alura.notepad.R;
import br.com.alura.notepad.dao.NoteDAO;
import br.com.alura.notepad.model.Note;
import br.com.alura.notepad.ui.recyclerview.adapter.NoteListAdapter;
import br.com.alura.notepad.ui.recyclerview.adapter.listener.OnItemClickListener;
import br.com.alura.notepad.ui.recyclerview.helper.callback.NoteItemTouchHelperCallback;

import static br.com.alura.notepad.ui.activity.constants.ConstantsAmongActivities.NOTE_INSERTION_REQUEST_CODE;
import static br.com.alura.notepad.ui.activity.constants.ConstantsAmongActivities.NOTE_KEY;
import static br.com.alura.notepad.ui.activity.constants.ConstantsAmongActivities.NOTE_POSITION_KEY;
import static br.com.alura.notepad.ui.activity.constants.ConstantsAmongActivities.NOTE_UPDATE_REQUEST_CODE;
import static br.com.alura.notepad.ui.activity.constants.ConstantsAmongActivities.POSITION_CHECK_VALUE;

public class NoteListActivity extends AppCompatActivity {

    private static final String APPBAR_TITLE = "Notepad";
    private final NoteDAO dao = new NoteDAO();
    private NoteListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        setTitle(APPBAR_TITLE);

        setupRecyclerView();
        setupInsertNoteBehaviour();
    }

    private void setupInsertNoteBehaviour() {
        TextView insertNote = findViewById(R.id.activity_note_list_insert_note);
        insertNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNoteFormActivityInsert();
            }
        });
    }

    private void startNoteFormActivityInsert() {
        Intent insertionIntent = new Intent(NoteListActivity.this, NoteFormActivity.class);
        startActivityForResult(insertionIntent, NOTE_INSERTION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (isValidInsertRequestCode(requestCode)) {
            if (isValidResultCode(resultCode)) {
                if (checkIntentData(data)) {
                    insertNote(data);
                }
            }
        }

        if (isValidUpdateRequestCode(requestCode)) {
            if (isValidResultCode(resultCode)) {
                if (checkIntentData(data)) {
                    updateNote(data);
                }
            }
        }
    }

    private boolean isValidInsertRequestCode(int requestCode) {
        return requestCode == NOTE_INSERTION_REQUEST_CODE;
    }

    private boolean isValidUpdateRequestCode(int requestCode) {
        return requestCode == NOTE_UPDATE_REQUEST_CODE;
    }

    private boolean isValidResultCode(int resultCode) {
        return resultCode == Activity.RESULT_OK;
    }

    private boolean checkIntentData(Intent data) {
        return data != null && data.hasExtra(NOTE_KEY);
    }

    private void insertNote(Intent data) {
        Note newNote = data.getParcelableExtra(NOTE_KEY);
        dao.insertNote(newNote);
        adapter.insertNote(newNote);
    }

    private void updateNote(Intent data) {
        Note updatedNote = data.getParcelableExtra(NOTE_KEY);
        int receivedPosition = data.getIntExtra(NOTE_POSITION_KEY, POSITION_CHECK_VALUE);
        if (receivedPosition > POSITION_CHECK_VALUE) {
            dao.updateNote(receivedPosition, updatedNote);
            adapter.updateNote(receivedPosition, updatedNote);
        } else {
            Toast.makeText(this, "An error occurred while updating the note!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupRecyclerView() {
        RecyclerView noteRecyclerView = findViewById(R.id.activity_note_list_recyclerview);
        setupAdapter(noteRecyclerView);
        setupItemTouchHelper(noteRecyclerView);
    }

    private void setupItemTouchHelper(RecyclerView recyclerView) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NoteItemTouchHelperCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void setupAdapter(RecyclerView recyclerView) {
        adapter = new NoteListAdapter(this, dao.getNoteList());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, Note note) {
                startNoteFormActivityUpdate(position, note);
            }
        });
    }

    private void startNoteFormActivityUpdate(int position, Note note) {
        Intent updateIntent = new Intent(NoteListActivity.this, NoteFormActivity.class);
        updateIntent.putExtra(NOTE_POSITION_KEY, position);
        updateIntent.putExtra(NOTE_KEY, note);
        startActivityForResult(updateIntent, NOTE_UPDATE_REQUEST_CODE);
    }
}
