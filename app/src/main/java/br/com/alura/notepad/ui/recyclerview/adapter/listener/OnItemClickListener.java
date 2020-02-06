package br.com.alura.notepad.ui.recyclerview.adapter.listener;

import br.com.alura.notepad.model.Note;

public interface OnItemClickListener {

    void onItemClick(int position, Note note);
}
