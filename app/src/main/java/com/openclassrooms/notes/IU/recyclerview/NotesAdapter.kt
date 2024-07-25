package com.openclassrooms.notes.IU.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.notes.databinding.NoteBinding
import com.openclassrooms.notes.model.Note

/**
 * An adapter for displaying a list of notes in a RecyclerView.
 * @param notes The list of notes to display.
 * Un adaptateur pour afficher une liste de notes dans un RecyclerView.
 * @param notes La liste des notes à afficher.
 */
class NotesAdapter(private var notes: List<Note>) : RecyclerView.Adapter<NoteViewHolder>() {

    /**
     * Updates the list of notes displayed by the adapter.
     * @param newNotes The new list of notes to display.
     * Met à jour la liste des notes affichées par l'adaptateur.
     * @param newNotes La nouvelle liste de notes à afficher.
     */
    fun updateNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyItemChanged(notes.size-1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int =
        notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }
}