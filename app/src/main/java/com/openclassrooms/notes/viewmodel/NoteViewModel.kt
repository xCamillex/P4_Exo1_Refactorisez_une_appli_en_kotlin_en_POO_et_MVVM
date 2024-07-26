package com.openclassrooms.notes.viewmodel

import androidx.lifecycle.ViewModel
import com.openclassrooms.notes.data.repository.NotesRepository
import com.openclassrooms.notes.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * ViewModel class responsible for managing the list of notes and interacting with the repository.
 * This class observes changes to the list of notes in the repository and provides them to the UI
 * through LiveData. It also contains logic for adding new notes.
 * @param notesRepository The repository responsible for providing access to note data.
 * Classe ViewModel chargée de gérer la liste des notes et d'interagir avec le dépôt (repository).
 * Cette classe observe les modifications apportées à la liste des notes dans le repository et les
 * fournit à l'interface utilisateur via LiveData.
 * Elle contient également une logique permettant d'ajouter de nouvelles notes.
 * @param notesRepository Le repository chargé de fournir l'accès aux données des notes.
 * Use Hilt to inject the used NoteRepository by the constructor
 */

@HiltViewModel
class NoteViewModel @Inject constructor (private val notesRepository: NotesRepository) : ViewModel (){
    /**
     * Fetch the note list,
     * by Exposing directly the Flow of the notesRepository
     * @return A Flow with the notes list.
     * Récupérer la liste des notes,
     * en exposant directement le flux du notesRepository
     * @return Un flux avec la liste des notes.
     */
    val notes: Flow<List<Note>> =  notesRepository.notes

    /**
     * Add a note to the list
     * @param newNote The new note object to add to the list.
     * Ajouter une note à la liste
     * @param newNote Le nouvel objet note à ajouter à la liste.
     */
    fun addNote(newNote:Note) = notesRepository.addNote(newNote)

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
    fun removeNote(index: Int?, noteObject:Note?, title: String?) = notesRepository.removeNote(index,noteObject,title)
}
