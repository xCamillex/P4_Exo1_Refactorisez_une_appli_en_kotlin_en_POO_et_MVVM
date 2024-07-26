package com.openclassrooms.notes.data.service

import com.openclassrooms.notes.model.Note

/**
 * Implementation of the [NotesApiService] interface that stores note in local
 * Implémentation de l'interface [NotesApiService] qui stocke les notes en local
 */
class LocalNotesApiService : NotesApiService {

    /**
     * Delegate property for the List of notes.
     * the initialization will be deferred until the first time the list will be accessed.
     * the use of "val" guarantees that generateDefaultNotes() is called only once.
     * after this, the Note list will behave like a classic Mutable list (add/remove).
     * Propriété déléguée pour la liste de notes.
     * l'initialisation sera différée jusqu'à la première fois où la liste sera consultée.
     * l'utilisation de "val" garantit que generateDefaultNotes() n'est appelé qu'une seule fois.
     * après cela, la liste de notes se comportera comme une liste mutable classique (ajout/suppression).
     */
    private val notesList by lazy {generateDefaultNotes() }

    /**
     * Add a note in the list of note.
     * @param note The note to add in the list of notes.
     */
    override fun addNote(note: Note) {
        notesList.add(note)
    }

    /**
     * Fetch the list of notes.
     * Used to get the list of all the Note.
     * @return A mutable list of Note objects.
     * Récupère la liste des notes.
     * Permet d'obtenir la liste de toutes les notes.
     * @return Une liste modifiable d'objets Note.
     */
    override fun getAllNotes(): MutableList<Note> {
        return notesList
    }

    /**
     * Generate a default list of notes.
     * This method is used to populate our list with example list of notes.
     * Used to the initialisation of the noteList delegate property.
     * @return A mutable list of notes populate with notes examples.
     * Génère une liste de notes par défaut.
     * Cette méthode est utilisée pour remplir notre liste avec une liste d'exemples de notes.
     * Utilisé pour l'initialisation de la propriété déléguée noteList.
     * @return Une liste mutable de notes remplie d'exemples de notes.
     */
    private fun generateDefaultNotes():MutableList<Note>{
        return mutableListOf (
            Note("La vie est belle", "La vie est belle, pleine de choses à voir et à faire. Profitez de chaque moment et ne laissez jamais personne vous dire que vous ne pouvez pas faire ce que vous voulez."),
            Note("Ne laissez personne vous dire que vous ne pouvez pas faire quelque chose.", "Croyez en vous et en vos capacités. Ne laissez personne vous dire que vous ne pouvez pas faire quelque chose. Suivez vos rêves et ne laissez rien vous arrêter."),
            Note("Suivez vos rêves", "Ne laissez rien vous arrêter de suivre vos rêves. Travaillez dur et ne vous découragez jamais. Vos rêves sont à votre portée, alors n'ayez pas peur de les poursuivre."),
            Note("Soyez gentil avec les autres", "Le monde a besoin de plus de gentillesse. Soyez gentil avec les autres, même si ce n'est pas facile. La gentillesse peut faire une grande différence dans le monde."),
            Note("Aidez les autres", "Le monde est un meilleur endroit lorsque nous travaillons ensemble. Aidez les autres, même si c'est juste un petit geste. Chaque geste compte."),
            Note("Soyez reconnaissant pour ce que vous avez.", "Il y a toujours quelqu'un qui a moins que vous. Soyez reconnaissant pour ce que vous avez, même si ce n'est pas grand-chose. La gratitude peut vous rendre heureux et épanoui."),
            Note("Vivez le moment présent", "Ne vous inquiétez pas du passé et ne vous inquiétez pas de l'avenir. Vivez le moment présent et profitez de chaque minute. Le moment présent est tout ce que vous avez"),
            Note("Prenez soin de vous", "Mangez sainement, faites de l'exercice et dormez suffisamment. Prenez soin de votre corps et de votre esprit. Vous êtes votre meilleur atout."),
            Note("Passez du temps avec vos proches", "Ils sont les plus importants dans votre vie. Passez du temps avec vos proches et montrez-leur à quel point vous les aimez. Ils sont votre famille et vos amis."),
            Note("Risez et amusez-vous.", "La vie est trop courte pour être sérieuse tout le temps. Riez et amusez-vous. Passez du temps à faire les choses que vous aimez."),
        )
    }
    /**
     * Delete a note in the list of notes.
     * This method must take almost one argument of your choice to remove the Note.
     * (by index (int) , by Object ref (Note), or by note title (String))
     * @param title null by default (can be omitted), used to remove by the title of the note.
     * @param index null by default (can be omitted), used to remove by the index position of the note.
     * @param noteObject null by default (can be omitted), used to remove by the object ref directly.
     * Supprimer une note dans la liste des notes.
     * Cette méthode doit prendre au moins un argument de notre choix pour supprimer la note.
     * (par index (int), par référence d'objet (Note), ou par titre de note (Chaîne))
     * @param title null par défaut (peut être omis), utilisé pour supprimer par le titre de la note.
     * @param index null par défaut (peut être omis), utilisé pour supprimer par la position d'index de la note.
     * @param noteObject null par défaut (peut être omis), utilisé pour supprimer directement par la référence d'objet.
     */
    override fun removeNote(index: Int?, noteObject: Note?, title: String?) {
        when {
            index != null -> notesList.removeAt(index)
            noteObject != null -> notesList.remove(noteObject)
            title != null -> notesList.removeIf { it.title == title }
            else -> throw IllegalArgumentException("Note not found")
        }
    }
}