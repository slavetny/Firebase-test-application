package com.slavetny.firebasetesttask.data.repository

import com.slavetny.firebasetesttask.domain.model.Note

interface NoteRepository {
    fun getNotes(notesCallback: (List<Note>) -> Unit)
    fun addNote(note: Note)
    fun removeNote(position: Int, removeNoteCallback: () -> Unit)
}