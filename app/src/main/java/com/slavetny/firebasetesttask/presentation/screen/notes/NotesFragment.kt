package com.slavetny.firebasetesttask.presentation.screen.notes

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.slavetny.firebasetesttask.R
import com.slavetny.firebasetesttask.domain.extension.observeNotNull
import com.slavetny.firebasetesttask.presentation.SwipeToDeleteCallback
import com.slavetny.firebasetesttask.presentation.adapter.NotesAdapter
import kotlinx.android.synthetic.main.fragment_notes.*
import org.koin.android.viewmodel.ext.android.viewModel

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private val viewModel: NotesViewModel by viewModel()
    private val adapter = NotesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setNotesLiveDataObserver()

        viewModel.getNotesList()

        addNoteButton.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_addNoteFragment)
        }
    }

    private fun setNotesLiveDataObserver() {
        viewModel.notesLiveData.observeNotNull(viewLifecycleOwner) { notes ->
            adapter.attachData(notes)
            progressBar.isVisible = false
        }
    }

    private fun initRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.removeNote(viewHolder.adapterPosition)
                progressBar.isVisible = true
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}