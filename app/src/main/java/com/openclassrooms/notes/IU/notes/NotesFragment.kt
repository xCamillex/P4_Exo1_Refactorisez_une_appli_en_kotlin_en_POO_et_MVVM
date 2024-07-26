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
     * Initializes the Floating Action Button (FAB) to add a new note.
     * When the FAB is clicked, a dialog is displayed allowing the user to enter the title and body
     * of the new note.
     * If the text fields are not empty, the new note is added via the `noteViewModel` and a Toast
     * notification is displayed.
     * If any of the fields are empty, a Toast notification informs the user that the title and body
     * cannot be empty.
     * Initialise le bouton flottant d'action (Floating Action Button, FAB) pour ajouter une
     * nouvelle note.
     * Lorsqu'on clique sur le FAB, un dialogue s'affiche permettant à l'utilisateur de saisir le
     * titre et le corps de la nouvelle note.
     * Si les champs de texte ne sont pas vides, la nouvelle note est ajoutée via le `noteViewModel`
     * et une notification Toast est affichée.
     * Si l'un des champs est vide, une notification Toast informe l'utilisateur que le titre et le
     * corps ne peuvent pas être vides.
     */
    private fun initFABButton() {
        binding.btnAdd.setOnClickListener {
            // Créer deux EditText pour obtenir la saisie du titre et du corps de la note
            val titleInput = EditText(requireContext()).apply {
                hint = "Entrer le titre"
            }
            val bodyInput = EditText(requireContext()).apply {
                hint = "Entrer le texte"
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
     * Collecte les notes du ViewModel et met à jour l'adaptateur.
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
         * Utilisez cette méthode d'usine pour créer une nouvelle instance de
         * ce fragment en utilisant les paramètres fournis.
         * @return Une nouvelle instance du fragment NoteFragment.
         */
        @JvmStatic
        fun newInstance() = NotesFragment()
    }
}