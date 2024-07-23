package com.openclassrooms.notes.data.repository

import com.openclassrooms.notes.data.service.LocalNotesApiService
import com.openclassrooms.notes.data.service.NotesApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository class for the notes.
 * Classe de référentiel pour les notes.
 */
class NotesRepository {

    /**
     * The API service for interacting with notes.
     * Le service API pour interagir avec les notes.
     */
    private val notesApiService: NotesApiService = LocalNotesApiService()

    /**
     * A flow that emits a list of all notes.
     * Un flux qui émet une liste de toutes les notes.
     */
    val notes: Flow<List<Pair<String, String>>> = flow {
        emit(notesApiService.getAllNotes())
    }
}