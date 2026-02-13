package com.example.food

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MyMealsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_meals)

        // 1. Receive data
        val email = intent.getStringExtra("USER_EMAIL") ?: "Guest"
        val userEmailText = findViewById<TextView>(R.id.userEmailText)
        userEmailText.text = email

        // 2. Setup Navigation
        findViewById<MaterialButton>(R.id.nutritionButton).setOnClickListener {
            startActivity(Intent(this, NutritionDetailActivity::class.java))
        }

        findViewById<MaterialButton>(R.id.statsButton).setOnClickListener {
            startActivity(Intent(this, CalorieStatsActivity::class.java))
        }

        findViewById<MaterialButton>(R.id.hydrationButton).setOnClickListener {
            startActivity(Intent(this, HydrationActivity::class.java))
        }
    }
}