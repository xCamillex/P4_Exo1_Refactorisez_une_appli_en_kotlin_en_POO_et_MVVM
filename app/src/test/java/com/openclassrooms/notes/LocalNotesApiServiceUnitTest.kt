package com.openclassrooms.notes

import com.openclassrooms.notes.data.service.LocalNotesApiService
import com.openclassrooms.notes.model.Note
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Test Class for the LocalNoteApiService
 */
class LocalNotesApiServiceUnitTest {
    private lateinit var notesApiService: LocalNotesApiService

    /**
     * Method called before each test to prepare the test environment.
     * This method initializes a new instance of `LocalNotesApiService` and assigns it to the `notesApiService` variable.
     * It ensures that each test runs with a fresh instance of `LocalNotesApiService`, providing a clean state for
     * each test and preventing test interference or side effects.
     * Méthode appelée avant chaque test pour préparer l'environnement de test.
     * Cette méthode initialise une nouvelle instance de `LocalNotesApiService` et l'affecte à la variable `notesApiService`.
     * Elle garantit que chaque test s'exécute avec une nouvelle instance de `LocalNotesApiService`, fournissant un état propre pour
     * chaque test et empêchant les interférences de test ou les effets secondaires.
     */
    @Before
    fun setUp() {
        notesApiService = LocalNotesApiService()
    }

    /**
     * Tests the `addNote` method to verify that it adds a note to the list.
     * Given(preconditions):
     * - An instance of `Note` with a specified title and content.
     * When(action to test):
     * - The `addNote` method is called with this note.
     * Then(expected result):
     * - The note must be present in the list returned by `getAllNotes`.
     * - A check is made to ensure that a note with the same title and content is found in the list.
     * Teste la méthode `addNote` pour vérifier qu'elle ajoute une note à la liste.
     * Given(préconditions):
     * - Une instance de `Note` avec un titre et un contenu spécifiés.
     * When(action à tester):
     * - La méthode `addNote` est appelée avec cette note.
     * Then(résultat attendu):
     * - La note doit être présente dans la liste retournée par `getAllNotes`.
     * - Une vérification est faite pour s'assurer qu'une note avec le même titre et contenu est trouvée dans la liste.
     */
    @Test
    fun `addNote should add a note to the list`() {
        // Given
        val note = Note("Titre", "Contenu")

        // When
        notesApiService.addNote(note)

        // Then
        val allNotes = notesApiService.getAllNotes()
        val foundNote = allNotes.find { it.title == note.title && it.body == note.body }
        assertNotNull(foundNote)
    }

    /**
     * Tests the `getAllNotes` method to verify that it returns all notes in the list.
     * Given:
     * - The target size of the list is calculated by adding 2 to the current size of the note list.
     * - Two `Note` instances (note1, note2) are added to the note list.
     * When:
     * - The `getAllNotes` method is called to retrieve all notes in the list.
     * Then:
     * - The returned note list must contain note1.
     * - The returned note list must contain note2.
     * - The size of the returned note list must match the calculated target size.
     * Teste la méthode `getAllNotes` pour vérifier qu'elle retourne toutes les notes de la liste.
     * Given:
     * - La taille cible de la liste est calculée en ajoutant 2 à la taille actuelle de la liste des notes.
     * - Deux instances de `Note` (note1, note2) sont ajoutées à la liste des notes.
     * When:
     * - La méthode `getAllNotes` est appelée pour récupérer toutes les notes de la liste.
     * Then:
     * - La liste des notes retournée doit contenir note1.
     * - La liste des notes retournée doit contenir note2.
     * - La taille de la liste des notes retournée doit correspondre à la taille cible calculée.
     */
    @Test
    fun `getAllNotes should return all the notes in the list`() {
        // Given
        val targetTestListSize = (notesApiService.getAllNotes().size + 2)
        val note1 = Note("Titre1", "Contenu1")
        val note2 = Note("Titre2", "Contenu2")
        notesApiService.addNote(note1)
        notesApiService.addNote(note2)

        // When
        val allNotes = notesApiService.getAllNotes()


        // Then
        assertTrue(allNotes.contains(note1)) // Verify note1 is present
        assertTrue(allNotes.contains(note2)) // Verify note2 is present
        assertEquals(targetTestListSize, allNotes.size) // Verify list size matches
    }

    /**
     * Tests the behavior of the private method `generateDefaultNotes`.
     * Given:
     * - The private method `generateDefaultNotes` of `notesApiService` is made accessible via reflection.
     * When:
     * - The `generateDefaultNotes` method is invoked to generate the list of default notes.
     * Then:
     * - The returned list of default notes must not be empty.
     * Teste le comportement de la méthode privée `generateDefaultNotes`.
     * Given:
     * - La méthode privée `generateDefaultNotes` de `notesApiService` est rendue accessible via réflexion.
     * When:
     * - La méthode `generateDefaultNotes` est invoquée pour générer la liste des notes par défaut.
     * Then:
     * - La liste des notes par défaut retournée ne doit pas être vide.
     */
    @Test
    fun `test GenerateDefaultNotes behavior`() {
        val method = notesApiService::class.java.getDeclaredMethod("generateDefaultNotes")
        method.isAccessible = true // Rend la méthode accessible
        val defaultNotes = method.invoke(notesApiService) as List<*>

        // Assertions sur defaultNotes
        assertTrue(defaultNotes.isNotEmpty())
    }

    /**
     * Tests the `removeNote` method to verify that it removes a note at the given index.
     * Given:
     * - Three `Note` instances (note1, note2, note3) are added to the note list.
     * - The size of the initial list is stored.
     * When:
     * - The `removeNote` method is called with the index of note2 (the second note).
     * Then:
     * - The size of the note list should be reduced by 1.
     * - The note list should still contain note1 and note3.
     * - The note list should no longer contain note2.
     * Teste la méthode `removeNote` pour vérifier qu'elle supprime une note à l'index donné.
     * Given:
     * - Trois instances de `Note` (note1, note2, note3) ajoutées à la liste des notes.
     * - La taille de la liste initiale est stockée.
     * When:
     * - La méthode `removeNote` est appelée avec l'index de note2 (deuxième note).
     * Then:
     * - La taille de la liste des notes doit être réduite de 1.
     * - La liste des notes doit toujours contenir note1 et note3.
     * - La liste des notes ne doit plus contenir note2.
     */
    @Test
    fun `removeNote by index should remove the note at the given index`() {
        // Given
        val note1 = Note("Titre1", "Contenu1")
        val note2 = Note("Titre2", "Contenu2")
        val note3 = Note("Titre3", "Contenu3")
        notesApiService.addNote(note1)
        notesApiService.addNote(note2)
        notesApiService.addNote(note3)

        val allNotes = notesApiService.getAllNotes()

        val expectedSize = allNotes.size - 1 // Expected size after removal

        // When
        notesApiService.removeNote(index = allNotes.size - 2) //remove note2 (from the end of the list)

        // Then
        assertEquals(expectedSize, allNotes.size) // Verify list size reduction
        assertTrue(allNotes.contains(note1)) // Verify note1 is present
        assertTrue(allNotes.contains(note3)) // Verify note3 is present
        assertFalse(allNotes.contains(note2)) // Verify note2 is absent
    }
}