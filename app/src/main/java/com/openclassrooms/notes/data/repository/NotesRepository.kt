package com.openclassrooms.notes.data.repository

import com.openclassrooms.notes.data.service.LocalNotesApiService
import com.openclassrooms.notes.data.service.NotesApiService
import com.openclassrooms.notes.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository class for the notes.
 * Classe de référentiel pour les notes.
 */
class NotesRepository @Inject constructor( private val notesApiService: NotesApiService){

    /**
     * A flow that emits a list of all notes.
     * Un flux qui émet une liste de toutes les notes.
     */
    val notes: Flow<List<Note>> = flow {
        emit(notesApiService.getAllNotes())
    }

    fun addNote(note: Note){
        notesApiService.addNote(note)
    }
}