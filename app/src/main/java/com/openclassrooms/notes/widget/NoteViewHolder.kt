package com.openclassrooms.notes.widget

import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.notes.databinding.NoteBinding

class NoteViewHolder(private val binding: NoteBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Pair<String, String>) {
        binding.title.text = note.first
        binding.body.text = note.second
    }

}