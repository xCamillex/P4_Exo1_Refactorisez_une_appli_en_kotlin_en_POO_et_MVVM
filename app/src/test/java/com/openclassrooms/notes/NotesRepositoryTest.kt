package com.openclassrooms.notes

import com.openclassrooms.notes.data.repository.NotesRepository
import com.openclassrooms.notes.data.service.NotesApiService
import com.openclassrooms.notes.model.Note
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

/**
 * Test class for the Notes Repository
 */
class NotesRepositoryTest {

    /**
     * Mocked class
     */
    @MockK
    private lateinit var mockNotesApiService: NotesApiService
    private lateinit var notesRepository: NotesRepository

    /**
     * Initialization method called before each test to set up the test environment.
     * This method prepares the objects needed for testing by:
     * 1. Initializing the MockK annotations to activate the mocks defined in this test class.
     * 2. Creating an instance of `NotesRepository` using a mock of `NotesApiService`.
     * Initializing with `MockKAnnotations.init(this)` configures the mocks annotated with `@MockK`
     * so that they are ready to be used in tests. Then, an instance of `NotesRepository` is created
     * with the mock of `NotesApiService`, ensuring that each test starts with a clean configuration
     * and isolated from the real implementation of `NotesApiService`.
     * Méthode d'initialisation appelée avant chaque test pour configurer l'environnement de test.
     * Cette méthode prépare les objets nécessaires pour les tests en :
     * 1. Initialisant les annotations MockK pour activer les mocks définis dans cette classe de test.
     * 2. Créant une instance de `NotesRepository` en utilisant un mock de `NotesApiService`.
     * L'initialisation avec `MockKAnnotations.init(this)` permet de configurer les mocks annotés
     * avec `@MockK` afin qu'ils soient prêts à être utilisés dans les tests. Ensuite, une instance
     * de `NotesRepository` est créée avec le mock de `NotesApiService`, garantissant que chaque
     * test commence avec une configuration propre et isolée de l'implémentation réelle de `NotesApiService`.
     */
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        notesRepository = NotesRepository(mockNotesApiService)
    }

    /**
     * Checks that the `NotesRepository` `addNote` method correctly calls the `NotesApiService` `addNote` method.
     * Given:
     * - A `Note` instance (newNote) with a specified title and content.
     * - The `mockNotesApiService` `addNote` method is configured to return nothing (Unit) when
     * called with `newNote`.
     * When:
     * - The `notesRepository` `addNote` method is called with `newNote`.
     * Then:
     * - The `mockNotesApiService` `addNote` method must be called exactly once with `newNote` as an argument.
     * Vérifie que la méthode `addNote` de `NotesRepository` appelle correctement la méthode
     * `addNote` de `NotesApiService`.
     * Given:
     * - Une instance de `Note` (newNote) avec un titre et un contenu spécifiés.
     * - La méthode `addNote` de `mockNotesApiService` est configurée pour ne rien retourner (Unit)
     * lorsqu'elle est appelée avec `newNote`.
     * When:
     * - La méthode `addNote` de `notesRepository` est appelée avec `newNote`.
     * Then:
     * - La méthode `addNote` de `mockNotesApiService` doit être appelée exactement une fois avec
     * `newNote` comme argument.
     */
    @Test
    fun `addNote calls notesApiService to add the note`(){

        val newNote = Note("Titre3", "msg3")
        // Configure le mock pour la méthode addNote
        every { mockNotesApiService.addNote(newNote) } returns Unit
        // Appel de la méthode addNote sur notesRepository
        notesRepository.addNote(newNote)
        // Vérification que addNote a été appelé exactement une fois avec newNote
        verify(exactly = 1) { mockNotesApiService.addNote(newNote) }
    }
}