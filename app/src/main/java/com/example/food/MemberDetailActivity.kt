package com.example.food

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MemberDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_detail)
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val nameTextView = findViewById<TextView>(R.id.memberNameDetail)
        val roleTextView = findViewById<TextView>(R.id.memberRole)
        val descTextView = findViewById<TextView>(R.id.memberDescription)
        val photoImageView = findViewById<ImageView>(R.id.memberPhoto)

        // Receive student name from intent
        val name = intent.getStringExtra("MEMBER_NAME")
        nameTextView.text = name

        // Set different data based on the student's name
        when (name) {
            "Dineshkumar" -> {
                roleTextView.text = "Role: Android Developer"
                descTextView.text = "Responsible for Android development and UI integration."
                photoImageView.setImageResource(android.R.drawable.ic_menu_edit)
            }
            "Harsith" -> {
                roleTextView.text = "Role: UI Designer"
                descTextView.text = "Designed the application layout and user experience."
                photoImageView.setImageResource(android.R.drawable.ic_menu_gallery)
            }
            "Gowsik" -> {
                roleTextView.text = "Role: Backend Developer"
                descTextView.text = "Managed backend logic and database structure."
                photoImageView.setImageResource(android.R.drawable.ic_menu_manage)
            }
            else -> {
                roleTextView.text = "Role: Team Member"
                descTextView.text = "Member of the project development team."
                photoImageView.setImageResource(android.R.drawable.ic_menu_myplaces)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}