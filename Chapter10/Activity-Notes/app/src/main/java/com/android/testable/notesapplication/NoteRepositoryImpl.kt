package com.android.testable.notesapplication

import androidx.lifecycle.LiveData
import java.util.concurrent.Executor

class NoteRepositoryImpl(
    private val executor: Executor,
    private val noteDao: NoteDao
) : NoteRepository {

    override fun insertNote(note: Note) {
        executor.execute {
            noteDao.insertNote(note)
        }
    }

    override fun getAllNotes(): LiveData<List<Note>> {
        return noteDao.loadNotes()
    }

    override fun getNoteCount(): LiveData<Int> {
        return noteDao.loadNoteCount()
    }
}