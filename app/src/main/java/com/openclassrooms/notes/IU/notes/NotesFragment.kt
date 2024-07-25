package com.openclassrooms.notes.IU.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.openclassrooms.notes.R
import com.openclassrooms.notes.databinding.FragmentNotesBinding
import com.openclassrooms.notes.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.openclassrooms.notes.IU.recyclerview.NotesAdapter
import kotlinx.coroutines.launch
import com.openclassrooms.notes.IU.recyclerview.NoteItemDecoration
import com.openclassrooms.notes.model.Note
import javax.inject.Inject

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private val notesAdapter = NotesAdapter(emptyList())
    @Inject
    lateinit var noteViewModel:NoteViewModel

    /**
     * - Life cycle -
     * Called when the fragment create the root view.
     * @param inflater the inflater to inflate the view.
     * @param container the container where to inflate the view.
     * @param savedInstanceState the bundle of the fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNotesBinding.inflate(inflater,container,false)
        return binding.root
    }

    /**
     * - Life cycle -
     * Called when the fragment view is created and ready.
     * @param view the view root of the fragment.
     * @param savedInstanceState the state bundle of the fragment.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initFABButton()
        collectNotes()
    }

    /**
     * Initializes the RecyclerView.
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

    /**
     * Initializes the FAB button.
     */
    private fun initFABButton() {
        binding.btnAdd.setOnClickListener {
            // Créer deux EditText pour obtenir la saisie du titre et du corps de la note
            val titleInput = EditText(requireContext()).apply {
                hint = "Enter title"
            }
            val bodyInput = EditText(requireContext()).apply {
                hint = "Enter body"
            }

            // Créer un LinearLayout pour contenir les deux EditText
            val layout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(16, 16, 16, 16)
                addView(titleInput)
                addView(bodyInput)
            }

            // Créer un AlertDialog pour obtenir la nouvelle note de l'utilisateur
            MaterialAlertDialogBuilder(requireContext()).apply {
                setTitle("Entrer une note")
                setView(layout)
                setPositiveButton("Ajouter") { dialog, which ->
                    val newTitle = titleInput.text.toString()
                    val newBody = bodyInput.text.toString()
                    if (newTitle.isNotBlank() && newBody.isNotBlank()) {
                        noteViewModel.addNote(Note(title = newTitle, body = newBody))
                        Toast.makeText(requireContext(), "Note added successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Title and body cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                }
                setNegativeButton("Annuler", null)
            }.show()
        }
    }

    /**
     * Collects notes from the ViewModel and updates the adapter.
     */
    private fun collectNotes() {
        lifecycleScope.launch {
            noteViewModel.notes.collect {
                notesAdapter.updateNotes(it)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment NoteFragment.
         */
        @JvmStatic
        fun newInstance() = NotesFragment()
    }
}