package com.openclassrooms.notes.IU.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.openclassrooms.notes.IU.recyclerview.NoteItemDecoration
import com.openclassrooms.notes.IU.recyclerview.NotesAdapter
import com.openclassrooms.notes.R
import com.openclassrooms.notes.data.repository.NotesRepository
import com.openclassrooms.notes.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

/**
 * The main activity for the app.
 * L'activité principale de l'application.
 */
class MainActivity : AppCompatActivity() {

    /**
     * The binding for the main layout.
     * La liaison pour la mise en page principale.
     */
    private lateinit var binding: ActivityMainBinding

    private val notesAdapter = NotesAdapter(emptyList())

    private val notesRepository = NotesRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initFABButton()
        collectNotes()
    }

    /**
     * Collects notes from the repository and updates the adapter.
     * Collecte les notes du référentiel et met à jour l'adaptateur.
     */
    private fun collectNotes() {
        lifecycleScope.launch {
            notesRepository.notes.collect {
                notesAdapter.updateNotes(it)
            }
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