package br.com.alura.notepad.ui.activity;

import android.app.Activity;
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
import static br.com.alura.notepad.ui.activity.constants.ConstantsAmongActivities.NOTE_POSITION_KEY;
import static br.com.alura.notepad.ui.activity.constants.ConstantsAmongActivities.POSITION_CHECK_VALUE;

public class NoteFormActivity extends AppCompatActivity {

    private static final String APPBAR_TITLE_INSERT = "Insert note";
    private static final String APPBAR_TITLE_UPDATE = "Edit note";
    private int receivedNotePosition = POSITION_CHECK_VALUE;
    private EditText noteTitle;
    private EditText noteDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_form);
        setTitle(APPBAR_TITLE_INSERT);

        initializeFields();
        getNoteAndPosition();
    }

    private void getNoteAndPosition() {
        Intent updateIntent = getIntent();
        if (updateIntent.hasExtra(NOTE_KEY)) {
            setTitle(APPBAR_TITLE_UPDATE);
            Note receivedNote = updateIntent.getParcelableExtra(NOTE_KEY);
            receivedNotePosition = updateIntent.getIntExtra(NOTE_POSITION_KEY, POSITION_CHECK_VALUE);
            assert receivedNote != null;
            setReceivedNoteFieldsInfo(receivedNote);
        }
    }

    private void initializeFields() {
        noteTitle = findViewById(R.id.activity_note_form_title);
        noteDescription = findViewById(R.id.activity_note_form_description);
    }

    private void setReceivedNoteFieldsInfo(Note receivedNote) {
        noteTitle.setText(receivedNote.getTitle());
        noteDescription.setText(receivedNote.getDescription());
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
        intent.putExtra(NOTE_POSITION_KEY, receivedNotePosition);
        setResult(Activity.RESULT_OK, intent);
    }

    private Note createNewNote() {
        noteTitle = findViewById(R.id.activity_note_form_title);
        noteDescription = findViewById(R.id.activity_note_form_description);
        return new Note(noteTitle.getText().toString(), noteDescription.getText().toString());
    }
}
