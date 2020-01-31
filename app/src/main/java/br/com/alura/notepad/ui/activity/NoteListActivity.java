package br.com.alura.notepad.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.alura.notepad.R;
import br.com.alura.notepad.dao.NoteDAO;
import br.com.alura.notepad.model.Note;
import br.com.alura.notepad.ui.adapter.recyclerview.NoteListAdapter;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        NoteDAO dao = new NoteDAO();
        for (int i = 1; i < 10000; i++) {
            dao.insert(new Note("New title " + i, "New description " + i));
        }

        RecyclerView noteList = findViewById(R.id.activity_note_list_recyclerview);
        noteList.setAdapter(new NoteListAdapter(this, dao.getNoteList()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        noteList.setLayoutManager(linearLayoutManager);
    }
}
