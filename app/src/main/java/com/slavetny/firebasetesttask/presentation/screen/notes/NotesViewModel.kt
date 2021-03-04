package com.slavetny.firebasetesttask.presentation.screen.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.slavetny.firebasetesttask.data.repository.NoteRepository
import com.slavetny.firebasetesttask.domain.model.Note
import com.slavetny.firebasetesttask.presentation.base.BaseViewModel

class NotesViewModel(
    private val repository: NoteRepository
) : BaseViewModel() {

    private val _notesLiveData = MutableLiveData<List<Note>>()
    val notesLiveData: LiveData<List<Note>> get() = _notesLiveData

    fun getNotesList() {
        repository.getNotes { notes ->
            _notesLiveData.postValue(notes)
        }
    }

    fun removeNote(position: Int) {
        repository.removeNote(position) {
            getNotesList()
        }
    }
}