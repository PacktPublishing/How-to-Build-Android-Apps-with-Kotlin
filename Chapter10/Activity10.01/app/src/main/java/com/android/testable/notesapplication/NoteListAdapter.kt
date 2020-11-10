package com.android.testable.notesapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_note_item.view.*

class NoteListAdapter(private val inflater: LayoutInflater) :
    RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private val noteList = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(inflater.inflate(R.layout.view_note_item, parent, false))
    }

    override fun getItemCount() = noteList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    fun replaceItems(notes: List<Note>) {
        noteList.clear()
        noteList.addAll(notes)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(note: Note) {
            containerView.view_note_list_text_view.text = note.text
        }
    }
}