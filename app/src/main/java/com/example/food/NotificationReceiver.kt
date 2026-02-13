package com.example.food

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "birthday_channel"

        // Set high importance for immediate delivery
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Birthday Notifications", NotificationManager.IMPORTANCE_HIGH)
            channel.description = "Channel for birthday reminders"
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Birthday Reminder")
            .setContentText("Wish your friend Happy Birthday!")
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Match high priority
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL) // Sound and vibration
            .build()

        notificationManager.notify(1001, notification)
    }
}