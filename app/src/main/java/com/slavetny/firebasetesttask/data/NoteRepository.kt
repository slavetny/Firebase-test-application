package com.slavetny.firebasetesttask.data

import com.slavetny.firebasetesttask.domain.model.Note

interface NoteRepository {
    fun addNote(note: Note)
}