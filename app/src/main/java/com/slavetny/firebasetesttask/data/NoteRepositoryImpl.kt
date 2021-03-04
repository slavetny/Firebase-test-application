package com.slavetny.firebasetesttask.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.slavetny.firebasetesttask.domain.constants.Constants
import com.slavetny.firebasetesttask.domain.model.Note

class NoteRepositoryImpl : NoteRepository {
    override fun addNote(note: Note) {

        val noteMap = hashMapOf(
            Constants.NOTE_TEXT to note.text,
            Constants.NOTE_DATE to note.date,
            Constants.NOTE_IMAGE to note.image
        )

        FirebaseFirestore.getInstance().collection(Constants.DB_NAME)
            .document(FirebaseAuth.getInstance().currentUser.uid)
            .collection(Constants.COLLECTION_NAME)
            .add(noteMap)
    }
}