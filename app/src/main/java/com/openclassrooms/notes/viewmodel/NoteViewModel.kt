package com.openclassrooms.notes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.notes.data.repository.NotesRepository
import com.openclassrooms.notes.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
 */

@HiltViewModel
class NoteViewModel @Inject constructor (private val notesRepository: NotesRepository) : ViewModel (){
    /**
     * LiveData object containing the list of notes.
     * Objet LiveData contenant la liste des notes.
     */
    private val noteLiveData = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> get() = noteLiveData

    /**
     * Initializes the ViewModel by collecting notes from the repository.
     * Initialise le ViewModel en collectant des notes à partir du dépôt.
     */
    init {
        collectNotes()
    }

    /**
     * Collects the list of notes from the repository and updates the LiveData object.
     * Collecte la liste des notes du dépôt et met à jour l'objet LiveData.
     */
    private fun collectNotes(){
        viewModelScope.launch {
            notesRepository.notes.collect {
                noteList -> noteLiveData.postValue(noteList)
            }
        }
    }

    /**
     * Function to add a new note.
     * Fonction permettant d'ajouter une nouvelle note.
     */
    fun addNewNote(title : String, body : String){
        if ( title.isNotBlank() && body.isNotBlank()){
            viewModelScope.launch {
                notesRepository.addNote(Note(title, body))
                collectNotes()
            }
        }
    }
}
