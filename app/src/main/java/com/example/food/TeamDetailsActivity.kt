package com.example.food

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class TeamDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)
        
        // Show back button in toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // FIX: Find the button and set the click listener
        val btnViewMembers = findViewById<MaterialButton>(R.id.btnViewTeamMembers)
        
        btnViewMembers.setOnClickListener {
            // FIX: Use explicit intent to navigate to TeamMembersActivity
            val intent = Intent(this, TeamMembersActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish() // Go back when the back button in toolbar is clicked
        return true
    }
}