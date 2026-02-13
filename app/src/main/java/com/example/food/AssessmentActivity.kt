package com.example.food

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class AssessmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assessment)

        val ageEditText = findViewById<TextInputEditText>(R.id.ageEditText)
        val weightEditText = findViewById<TextInputEditText>(R.id.weightEditText)
        val submitButton = findViewById<MaterialButton>(R.id.submitButton)

        submitButton.setOnClickListener {
            val age = ageEditText.text.toString()
            val weight = weightEditText.text.toString()

            if (age.isEmpty() || weight.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MyMealsActivity::class.java)
                intent.putExtra("AGE", age)
                intent.putExtra("WEIGHT", weight)
                startActivity(intent)
                finish()
            }
        }
    }
}