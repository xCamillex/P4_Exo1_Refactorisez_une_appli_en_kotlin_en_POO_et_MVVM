package com.openclassrooms.notes.model

/**
 * Data class representing a Note.
 * @property title The title of the note.
 * @property body The body content of the note.
 * This data class automatically provides:
 * - `equals()`: To compare two `Note` objects for equality.
 * - `hashCode()`: To generate a hash code for the `Note` object.
 * - `toString()`: To generate a string representation of the `Note` object.
 * - `copy()`: To create a copy of the `Note` object with optional modifications.
 * - `component1()` and `component2()`: To enable destructuring declarations.
 * Classe de données représentant une note.
 * @property title Le titre de la note.
 * @property body Le contenu du corps de la note.
 * Cette classe de données fournit automatiquement:
 * - `equals()`: pour comparer deux objets `Note` pour l'égalité.
 * - `hashCode()`: pour générer un code de hachage pour l'objet `Note`.
 * - `toString()`: pour générer une représentation sous forme de chaîne de l'objet `Note`.
 * - `copy()`: pour créer une copie de l'objet `Note` avec des modifications facultatives.
 * - `component1()` et `component2()`: pour activer les déclarations de déstructuration.
*/
data class Note(val title:String, val body:String)
