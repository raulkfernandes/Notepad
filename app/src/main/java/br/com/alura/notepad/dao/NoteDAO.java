package br.com.alura.notepad.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.alura.notepad.model.Note;

public class NoteDAO {

    private final static List<Note> noteList = new ArrayList<>();

    public List<Note> getNoteList() {
        return new ArrayList<>(noteList);
    }

    public void insertNote(Note... notes) {
        noteList.addAll(Arrays.asList(notes));
    }

    public void updateNote(int index, Note note) {
        noteList.set(index, note);
    }

    public void removeNote(int index) {
        noteList.remove(index);
    }

    public void swapNotes(int startIndex, int finalIndex) {
        Collections.swap(noteList, startIndex, finalIndex);
    }

    @SuppressWarnings("unused")
    public void clear() {
        noteList.clear();
    }
}
