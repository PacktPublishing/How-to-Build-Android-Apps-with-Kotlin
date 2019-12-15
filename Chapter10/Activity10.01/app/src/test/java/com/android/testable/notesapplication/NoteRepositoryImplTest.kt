package com.android.testable.notesapplication

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class NoteRepositoryImplTest {

    @InjectMocks
    lateinit var noteRepository: NoteRepositoryImpl
    @Mock
    lateinit var executor: Executor
    @Mock
    lateinit var noteDao: NoteDao

    @Test
    fun insertNote() {
        val note = Note(10, "text")
        doAnswer {
            (it.arguments[0] as Runnable).run()
        }.`when`(executor).execute(ArgumentMatchers.any())

        noteRepository.insertNote(note)

        verify(noteDao).insertNote(note)

    }

    @Test
    fun getAllNotes() {
        val notes = mock(LiveData::class.java)
        `when`(noteDao.loadNotes()).thenReturn(notes as LiveData<List<Note>>)

        val result = noteRepository.getAllNotes()

        assertEquals(notes, result)
    }

    @Test
    fun getNoteCount() {
        val count = mock(LiveData::class.java)
        `when`(noteDao.loadNoteCount()).thenReturn(count as LiveData<Int>)

        val result = noteRepository.getNoteCount()

        assertEquals(count, result)
    }
}