package com.slavetny.firebasetesttask.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.slavetny.firebasetesttask.R
import com.slavetny.firebasetesttask.domain.extension.inflate
import com.slavetny.firebasetesttask.domain.model.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var noteList: List<Note> = listOf()

    fun attachData(noteList: List<Note>) {
        this.noteList = noteList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NotesViewHolder(
            parent.inflate(R.layout.item_note)
        )

    override fun getItemCount() = noteList.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            itemView.noteText.text = note.text
            itemView.noteDate.text = note.date

            if (note.image.isEmpty()) {
                itemView.noteImage.isVisible = false
            } else {
                FirebaseStorage.getInstance().reference
                    .child(note.image).downloadUrl.addOnSuccessListener { imageUri ->
                        Glide.with(itemView)
                            .load(imageUri)
                            .into(itemView.noteImage)
                    }
            }
        }
    }
}