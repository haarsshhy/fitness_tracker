package com.example.food

import android.content.Intent
import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showMenuButton = findViewById<MaterialButton>(R.id.showMenuButton)

        showMenuButton.setOnClickListener { view ->
            // 1. Create the PopupMenu
            val popup = PopupMenu(this, view)
            
            // 2. Inflate the menu XML
            popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)

            // 3. Handle item clicks
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.popup_about -> {
                        startActivity(Intent(this, AboutUsActivity::class.java))
                        true
                    }
                    R.id.popup_team_details -> {
                        startActivity(Intent(this, TeamDetailsActivity::class.java))
                        true
                    }
                    R.id.popup_project_description -> {
                        startActivity(Intent(this, ProjectDescriptionActivity::class.java))
                        true
                    }
                    else -> false
                }
            }
            
            // 4. Show the menu
            popup.show()
        }
    }
}