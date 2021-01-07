package com.android.testable.notesapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class CountNotesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_count_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel =
            ViewModelProvider(requireActivity()).get(CountNotesViewModel::class.java)
        viewModel.getNoteCountLiveData().observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.fragment_count_notes_text_view).text = getString(R.string.total, it)
        })
        view.findViewById<Button>(R.id.fragment_count_notes_button).setOnClickListener {
            viewModel.insertNote(view.findViewById<EditText>(R.id.fragment_count_edit_text).text.toString())
        }
    }

}