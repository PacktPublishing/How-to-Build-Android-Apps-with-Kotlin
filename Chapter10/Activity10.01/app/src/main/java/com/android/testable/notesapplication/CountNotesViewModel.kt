package com.android.testable.notesapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class CountNotesViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository: NoteRepository = (application as NotesApplication).noteRepository

    fun insertNote(text: String) {
        noteRepository.insertNote(Note(0, text))
    }

    fun getNoteCountLiveData(): LiveData<Int> = noteRepository.getNoteCount()

}