package com.openclassrooms.notes.IU.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.openclassrooms.notes.IU.recyclerview.NoteItemDecoration
import com.openclassrooms.notes.IU.recyclerview.NotesAdapter
import com.openclassrooms.notes.R
import com.openclassrooms.notes.data.repository.NotesRepository
import com.openclassrooms.notes.databinding.ActivityMainBinding
import com.openclassrooms.notes.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * The main activity for the app.
 * L'activité principale de l'application.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /**
     * The binding for the main layout.
     * La liaison pour la mise en page principale.
     */
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var noteViewModel: NoteViewModel

    private val notesAdapter = NotesAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initFABButton()
        observeNotes()
    }

    /**
     * Observes the list of notes from in the ViewModel and updates the RecyclerView adapter accordingly.
     * Observe la liste des notes dans ViewModel et met à jour l'adaptateur RecyclerView en conséquence.
     */
    private fun observeNotes() {
        noteViewModel.notes.observe(this) {
            notes -> notesAdapter.updateNotes(notes)
            }
        }

    /**
     * Initializes the FAB button.
     * Initialise le bouton FAB.
     */
    private fun initFABButton() {
        binding.btnAdd.setOnClickListener {
            MaterialAlertDialogBuilder(this).apply {
                setTitle(R.string.coming_soon)
                setMessage(R.string.not_available_yet)
                setPositiveButton(android.R.string.ok, null)
            }.show()
        }
    }

    /**
     * Initializes the RecyclerView.
     * Initialise le RecyclerView.
     */
    private fun initRecyclerView() {
        with(binding.recycler) {
            addItemDecoration(
                NoteItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.default_margin),
                    resources.getInteger(R.integer.span_count)
                )
            )

            adapter = notesAdapter
        }

    }

}