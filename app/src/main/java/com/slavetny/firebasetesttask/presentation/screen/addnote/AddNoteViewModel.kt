package com.slavetny.firebasetesttask.presentation.screen.addnote

import com.slavetny.firebasetesttask.data.repository.NoteRepository
import com.slavetny.firebasetesttask.domain.model.Note
import com.slavetny.firebasetesttask.presentation.base.BaseViewModel

class AddNoteViewModel(
    private val noteRepository: NoteRepository
) : BaseViewModel() {

    var imageUri: String = ""

    fun addNote(note: Note) = noteRepository.addNote(note)
}