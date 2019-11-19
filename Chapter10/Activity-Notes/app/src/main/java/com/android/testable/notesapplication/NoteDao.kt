package com.android.testable.notesapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Query("SELECT * FROM notes")
    fun loadNotes(): LiveData<List<Note>>

    @Query("SELECT count(*) FROM notes")
    fun loadNoteCount(): LiveData<Int>
}