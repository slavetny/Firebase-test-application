package com.slavetny.firebasetesttask.presentation.screen.addnote

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.storage.FirebaseStorage
import com.slavetny.firebasetesttask.R
import com.slavetny.firebasetesttask.domain.constants.Constants
import com.slavetny.firebasetesttask.domain.model.Note
import kotlinx.android.synthetic.main.fragment_add_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class AddNoteFragment : Fragment(R.layout.fragment_add_note) {

    private val viewModel: AddNoteViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveNoteButton.setOnClickListener {
            val note = Note(
                noteTextField.text.toString(),
                getCurrentDate(),
                viewModel.imageUri
            )

            viewModel.addNote(note)

            findNavController().popBackStack()
        }

        choosePhotoButton.setOnClickListener {
            openGalleryForImage()
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, Constants.GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == Constants.GALLERY_REQUEST_CODE) {
            FirebaseStorage.getInstance().getReference(data?.data?.path!!).putFile(data.data!!)

            viewModel.imageUri = data.data?.path!!
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        return sdf.format(Date())
    }
}