package com.android.testable.notesapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoteListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.fragment_note_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NoteListAdapter(LayoutInflater.from(context))
        recyclerView.adapter = adapter
        val viewModel =
            ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return NoteListViewModel((requireActivity().application as NotesApplication).noteRepository) as T
                }

            }).get(NoteListViewModel::class.java)
        viewModel.getNoteListLiveData().observe(viewLifecycleOwner, Observer {
            adapter.replaceItems(it)
        })
    }
}