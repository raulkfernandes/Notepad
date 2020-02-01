package br.com.alura.notepad.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import br.com.alura.notepad.R;
import br.com.alura.notepad.dao.NoteDAO;
import br.com.alura.notepad.model.Note;
import br.com.alura.notepad.ui.adapter.recyclerview.NoteListAdapter;

import static br.com.alura.notepad.ui.activity.ConstantBetweenActivities.NOTE_KEY;
import static br.com.alura.notepad.ui.activity.ConstantBetweenActivities.NOTE_REQUEST_CODE;
import static br.com.alura.notepad.ui.activity.ConstantBetweenActivities.NOTE_RESULT_CODE;

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
                startInsertNoteActivity();
            }
        });
    }

    private void startInsertNoteActivity() {
        Intent intent = new Intent(NoteListActivity.this, InsertNoteActivity.class);
        startActivityForResult(intent, NOTE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (isValidResult(requestCode, resultCode, data)) {
            Note newNote = data.getParcelableExtra(NOTE_KEY);
            dao.insert(newNote);
            adapter.addNewNote(newNote);
        }
    }

    private boolean isValidResult(int requestCode, int resultCode, @Nullable Intent data) {
        return requestCode == NOTE_REQUEST_CODE && resultCode == NOTE_RESULT_CODE && data.hasExtra(NOTE_KEY);
    }

    private void setupRecyclerView() {
        RecyclerView noteRecyclerView = findViewById(R.id.activity_note_list_recyclerview);
        setupAdapter(noteRecyclerView);
    }

    private void setupAdapter(RecyclerView recyclerView) {
        adapter = new NoteListAdapter(this, dao.getNoteList());
        recyclerView.setAdapter(adapter);
    }
}
