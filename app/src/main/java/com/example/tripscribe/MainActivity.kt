package com.example.tripscribe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.tripscribe.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val authButton: FloatingActionButton = binding.authButton

        // prepare our List view and RecyclerView (cells)
        setupRecyclerView(binding.itemList)
        setupAuthButton(UserData, authButton)

        UserData.isSignedIn.observe(this, Observer<Boolean> { isSignedUp ->
            // update UI
            Log.i(TAG, "isSignedIn changed : $isSignedUp")

            if (isSignedUp) {
                authButton.setImageResource(R.drawable.ic_baseline_lock_open)
            } else {
                authButton.setImageResource(R.drawable.ic_baseline_lock)
            }
        })
    }

    // recycler view is the list of cells
    private fun setupRecyclerView(recyclerView: RecyclerView) {

        // update individual cell when the Note data are modified
        UserData.notes().observe(this, Observer<MutableList<UserData.Note>> { notes ->
            Log.d(TAG, "Note observer received ${notes.size} notes")

            // let's create a RecyclerViewAdapter that manages the individual cells
            recyclerView.adapter = NoteRecyclerViewAdapter(notes)
        })
    }

    private fun setupAuthButton(userData: UserData, authButton: FloatingActionButton) {
        // register a click listener
        authButton.setOnClickListener { view ->
            if (userData.isSignedIn.value!!) {
                authButton.setImageResource(R.drawable.ic_baseline_lock_open)
                AmplifyBackend.signOut()
            } else {
                authButton.setImageResource(R.drawable.ic_baseline_lock_open)
                AmplifyBackend.signIn(this)
            }
        }
    }

    // receive the web redirect after authentication
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        AmplifyBackend.handleWebUISignInResponse(requestCode, resultCode, data)
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}