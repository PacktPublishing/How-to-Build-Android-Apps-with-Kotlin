package com.android.testable.notesapplication

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CountNotesViewModelTest {

    private lateinit var countNotesViewModel: CountNotesViewModel
    @Mock
    lateinit var application: NotesApplication
    @Mock
    lateinit var noteRepository: NoteRepository

    @Before
    fun setUp() {
        Mockito.`when`(application.noteRepository).thenReturn(noteRepository)
        countNotesViewModel = CountNotesViewModel(application)
    }

    @Test
    fun insertNote() {
        val text = "text"
        countNotesViewModel.insertNote(text)
        Mockito.verify(noteRepository).insertNote(Note(0, text))
    }

    @Test
    fun getNoteCountLiveData() {
        val notes = Mockito.mock(LiveData::class.java)
        Mockito.`when`(noteRepository.getNoteCount()).thenReturn(notes as LiveData<Int>)

        val result = countNotesViewModel.getNoteCountLiveData()

        assertEquals(notes, result)
    }
}