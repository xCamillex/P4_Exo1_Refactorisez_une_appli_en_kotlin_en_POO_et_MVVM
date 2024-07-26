package com.openclassrooms.notes.data.repository

import com.openclassrooms.notes.data.service.LocalNotesApiService
import com.openclassrooms.notes.data.service.NotesApiService
import com.openclassrooms.notes.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository class for the notes.
 * Use dependency injection to get the the used Api service.
 * Classe de référentiel pour les notes.
 * Utilisez l’injection de dépendances pour obtenir le service API utilisé.
 */
class NotesRepository @Inject constructor( private val notesApiService: NotesApiService){

    /**
     * A flow that emits a list of all notes.
     * Un flux qui émet une liste de toutes les notes.
     */
    val notes: Flow<List<Note>> = flow {
        emit(notesApiService.getAllNotes())
    }

    /**
     * Add a note to the list of notes
     * @param newNote The new note object to add to the list.
     */
    fun addNote(newNote: Note) =
        notesApiService.addNote(newNote)

    /**
     * Remove a note to the list of notes.
     * Use one of the 3 parameters to remove a note object.
     * @param index (optional) the index position of the note to remove.
     * @param noteObject (optional) the note object reference to remove of the list.
     * @param title (optional) the title of the note to remove.
     * Supprimer une note de la liste des notes.
     * Utiliser l'un des 3 paramètres pour supprimer un objet note.
     * @param index (facultatif) la position d'index de la note à supprimer.
     * @param noteObject (facultatif) la référence de l'objet note à supprimer de la liste.
     * @param title (facultatif) le titre de la note à supprimer.
     */
    fun removeNote(index: Int?, noteObject:Note?, title: String?) = notesApiService.removeNote(index,noteObject,title)

}