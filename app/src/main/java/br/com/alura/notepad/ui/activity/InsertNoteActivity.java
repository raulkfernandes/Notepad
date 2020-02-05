package br.com.alura.notepad.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.notepad.R;
import br.com.alura.notepad.model.Note;

import static br.com.alura.notepad.ui.activity.constants.ConstantsAmongActivities.NOTE_KEY;
import static br.com.alura.notepad.ui.activity.constants.ConstantsAmongActivities.NOTE_RESULT_CODE;

public class InsertNoteActivity extends AppCompatActivity {

    private static final String APPBAR_TITLE = "Insert note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_note);
        setTitle(APPBAR_TITLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_insert_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_insert_note_icon) {
            Note newNote = createNewNote();
            returnNewNote(newNote);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void returnNewNote(Note note) {
        Intent intent = new Intent();
        intent.putExtra(NOTE_KEY, note);
        setResult(NOTE_RESULT_CODE, intent);
    }

    private Note createNewNote() {
        EditText noteTitle = findViewById(R.id.activity_insert_note_title);
        EditText noteDescription = findViewById(R.id.activity_insert_note_description);
        return new Note(noteTitle.getText().toString(), noteDescription.getText().toString());
    }
}
