package com.android.testable.notesapplication

import androidx.lifecycle.LiveData

interface NoteRepository {

    fun insertNote(note: Note)

    fun getAllNotes(): LiveData<List<Note>>

    fun getNoteCount(): LiveData<Int>
}