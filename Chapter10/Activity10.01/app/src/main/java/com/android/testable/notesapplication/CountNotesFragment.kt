package com.android.testable.notesapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_count_notes.*

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
            fragment_count_notes_text_view.text = getString(R.string.total, it)
        })
        fragment_count_notes_button.setOnClickListener {
            viewModel.insertNote(fragment_count_edit_text.text.toString())
        }
    }

}