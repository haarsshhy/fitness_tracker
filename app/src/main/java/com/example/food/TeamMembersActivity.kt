package com.example.food

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class TeamMembersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_members)
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Find buttons
        val btnMember1 = findViewById<MaterialButton>(R.id.btnMember1)
        val btnMember2 = findViewById<MaterialButton>(R.id.btnMember2)
        val btnMember3 = findViewById<MaterialButton>(R.id.btnMember3)

        // Dineshkumar
        btnMember1.setOnClickListener {
            val intent = Intent(this, MemberDetailActivity::class.java)
            intent.putExtra("MEMBER_NAME", "Dineshkumar")
            startActivity(intent)
        }

        // Harsith
        btnMember2.setOnClickListener {
            val intent = Intent(this, MemberDetailActivity::class.java)
            intent.putExtra("MEMBER_NAME", "Harsith")
            startActivity(intent)
        }

        // Gowsik
        btnMember3.setOnClickListener {
            val intent = Intent(this, MemberDetailActivity::class.java)
            intent.putExtra("MEMBER_NAME", "Gowsik")
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}