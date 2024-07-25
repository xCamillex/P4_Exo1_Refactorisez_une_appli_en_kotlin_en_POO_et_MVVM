package com.openclassrooms.notes.IU.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.openclassrooms.notes.R
import com.openclassrooms.notes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState== null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_fragment_Container,NotesFragment.newInstance())
                .commitNow()
        }
    }
}