package com.openclassrooms.notes.IU.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.notes.databinding.NoteBinding

/**
 * A view holder for displaying a note in a RecyclerView.
 * @param binding The binding for the note layout.
 * Un support de vue pour afficher une note dans un RecyclerView.
 * @param binding La liaison pour la mise en page de la note.
 */
class NoteViewHolder(private val binding: NoteBinding): RecyclerView.ViewHolder(binding.root) {

    /**
     * Binds the view holder to a note.
     * @param note The note to bind to the view holder.
     * Lie le détenteur de la vue à une note.
     * @param note La note à lier au détenteur de la vue.
     */
    fun bind(note: Pair<String, String>) {
        binding.title.text = note.first
        binding.body.text = note.second
    }

}