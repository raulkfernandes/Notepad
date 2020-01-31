package br.com.alura.notepad.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import br.com.alura.notepad.R;
import br.com.alura.notepad.dao.NoteDAO;
import br.com.alura.notepad.model.Note;
import br.com.alura.notepad.ui.adapter.recyclerview.NoteListAdapter;

public class NoteListActivity extends AppCompatActivity {

    NoteDAO dao = new NoteDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        initializeTestNotes();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView noteList = findViewById(R.id.activity_note_list_recyclerview);
        noteList.setAdapter(new NoteListAdapter(this, dao.getNoteList()));
    }

    private void initializeTestNotes() {
//        for (int i = 1; i < 10000; i++) {
//            dao.insert(new Note("New title " + i, "New description " + i));
//        }
        dao.insert(new Note("New title", "New Description"),
                new Note("Another title", "A bigger description to check layout changes"));
    }
}
