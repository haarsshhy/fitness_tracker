package com.example.food

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class LoginActivity : AppCompatActivity() {

    private lateinit var dateTimeDisplay: TextView
    private lateinit var loginProgressBar: ProgressBar
    private var selectedCalendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createNotificationChannel()

        val emailEditText = findViewById<TextInputEditText>(R.id.emailEditText)
        val loginButton = findViewById<MaterialButton>(R.id.loginButton)
        val assessmentButton = findViewById<MaterialButton>(R.id.assessmentButton)
        val pickDateTimeButton = findViewById<MaterialButton>(R.id.pickDateTimeButton)
        val showMenuButton = findViewById<MaterialButton>(R.id.showMenuButton)
        dateTimeDisplay = findViewById(R.id.dateTimeDisplay)
        loginProgressBar = findViewById(R.id.loginProgressBar)

        // POPUP MENU LOGIC
        showMenuButton.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)
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
            popup.show()
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
            } else {
                loginProgressBar.visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    loginProgressBar.visibility = View.GONE
                    sendLoginNotification()
                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MyMealsActivity::class.java)
                    intent.putExtra("USER_EMAIL", email)
                    startActivity(intent)
                }, 2000)
            }
        }

        pickDateTimeButton.setOnClickListener {
            showDatePicker()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showExitDialog()
            }
        })

        assessmentButton.setOnClickListener {
            startActivity(Intent(this, AssessmentActivity::class.java))
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(this, { _, year, month, day ->
            selectedCalendar.set(Calendar.YEAR, year)
            selectedCalendar.set(Calendar.MONTH, month)
            selectedCalendar.set(Calendar.DAY_OF_MONTH, day)
            showTimePicker()
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        TimePickerDialog(this, { _, hour, minute ->
            selectedCalendar.set(Calendar.HOUR_OF_DAY, hour)
            selectedCalendar.set(Calendar.MINUTE, minute)
            selectedCalendar.set(Calendar.SECOND, 0)
            dateTimeDisplay.text = "Reminder: ${selectedCalendar.time}"
            scheduleBirthdayNotification(selectedCalendar.timeInMillis)
            Toast.makeText(this, "Birthday Reminder Set!", Toast.LENGTH_SHORT).show()
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
    }

    private fun scheduleBirthdayNotification(timeInMillis: Long) {
        val intent = Intent(this, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        try {
            alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
        } catch (e: Exception) {
            Toast.makeText(this, "Could not set alarm: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun sendLoginNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(this, "login_channel")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("App Access")
            .setContentText("You have successfully logged in.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        notificationManager.notify(1, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Login Notifications"
            val channel = NotificationChannel("login_channel", name, NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun showExitDialog() {
        AlertDialog.Builder(this)
            .setTitle("Exit App")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("YES") { _, _ -> finishAffinity() }
            .setNegativeButton("NO", null)
            .show()
    }
}