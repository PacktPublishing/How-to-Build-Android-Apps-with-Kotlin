package com.android.testable.notesapplication

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NoteListViewModelTest {

    @InjectMocks
    lateinit var noteListViewModel: NoteListViewModel
    @Mock
    lateinit var noteRepository: NoteRepository

    @Test
    fun getNoteListLiveData() {
        val notes = Mockito.mock(LiveData::class.java)
        Mockito.`when`(noteRepository.getAllNotes()).thenReturn(notes as LiveData<List<Note>>)

        val result = noteListViewModel.getNoteListLiveData()

        assertEquals(notes, result)
    }
}