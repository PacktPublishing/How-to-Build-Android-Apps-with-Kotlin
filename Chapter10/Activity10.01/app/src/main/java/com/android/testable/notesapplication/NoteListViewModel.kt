package com.android.testable.notesapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class NoteListViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    fun getNoteListLiveData(): LiveData<List<Note>> = noteRepository.getAllNotes()
}