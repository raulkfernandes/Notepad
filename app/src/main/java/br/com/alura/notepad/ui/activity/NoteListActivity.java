package br.com.alura.notepad.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.notepad.R;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
    }
}
