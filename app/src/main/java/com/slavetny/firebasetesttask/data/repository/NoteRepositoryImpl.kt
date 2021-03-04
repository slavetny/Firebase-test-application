package com.slavetny.firebasetesttask.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.slavetny.firebasetesttask.domain.constants.Constants
import com.slavetny.firebasetesttask.domain.model.Note

class NoteRepositoryImpl : NoteRepository {
    override fun getNotes(notesCallback: (List<Note>) -> Unit) {
        FirebaseFirestore.getInstance().collection(Constants.DB_NAME)
            .document(getUId())
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

                notesCallback(noteList)
            }
    }

    override fun addNote(note: Note) {
        val noteMap = hashMapOf(
            Constants.NOTE_TEXT to note.text,
            Constants.NOTE_DATE to note.date,
            Constants.NOTE_IMAGE to note.image
        )

        FirebaseFirestore.getInstance().collection(Constants.DB_NAME)
            .document(getUId())
            .collection(Constants.COLLECTION_NAME)
            .add(noteMap)
    }

    override fun removeNote(position: Int, removeNoteCallback: () -> Unit) {
        FirebaseFirestore.getInstance().collection(Constants.DB_NAME)
            .document(getUId())
            .collection(Constants.COLLECTION_NAME).get().addOnSuccessListener {
                it.documents[position]?.reference?.delete()
                removeNoteCallback()
            }
    }

    private fun getUId() = FirebaseAuth.getInstance().currentUser!!.uid
}