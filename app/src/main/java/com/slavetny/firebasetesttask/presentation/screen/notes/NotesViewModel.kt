package com.slavetny.firebasetesttask.presentation.screen.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.slavetny.firebasetesttask.domain.constants.Constants
import com.slavetny.firebasetesttask.domain.model.Note
import com.slavetny.firebasetesttask.presentation.base.BaseViewModel

class NotesViewModel : BaseViewModel() {

    private val _notesLiveData = MutableLiveData<List<Note>>()
    val notesLiveData: LiveData<List<Note>> get() = _notesLiveData

    fun getNotesList() {
        FirebaseFirestore.getInstance().collection(Constants.DB_NAME)
            .document(FirebaseAuth.getInstance().currentUser.uid)
            .collection(Constants.COLLECTION_NAME).get().addOnSuccessListener {
                val noteList = ArrayList<Note>()

                for (i in 0 until it.documents.size) {
                    noteList.add(
                        Note(
                            it.documents[i].data?.get(Constants.NOTE_TEXT).toString(),
                            it.documents[i].data?.get(Constants.NOTE_DATE).toString(),
                            it.documents[i].data?.get(Constants.NOTE_IMAGE).toString()
                        )
                    )
                }

                _notesLiveData.postValue(noteList)
            }
    }

    fun removeNote(position: Int) {
        FirebaseFirestore.getInstance().collection(Constants.DB_NAME)
            .document(FirebaseAuth.getInstance().currentUser.uid)
            .collection(Constants.COLLECTION_NAME).get().addOnSuccessListener {
                it.documents[position]?.reference?.delete()
                getNotesList()
            }
    }
}