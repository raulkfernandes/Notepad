package br.com.alura.notepad.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.notepad.R;
import br.com.alura.notepad.dao.NoteDAO;
import br.com.alura.notepad.model.Note;

public class InsertNoteActivity extends AppCompatActivity {

    NoteDAO dao = new NoteDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_insert_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_insert_note_icon) {
            EditText noteTitle = findViewById(R.id.activity_insert_note_title);
            EditText noteDescription = findViewById(R.id.activity_insert_note_description);

            Note newNote = new Note(noteTitle.getText().toString(), noteDescription.getText().toString());
            dao.insert(newNote);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
