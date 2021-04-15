package com.example.navia.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.navia.R
import com.example.navia.view.activity.MainActivity


class MealNotification(base: Context, var mealName: String) : ContextWrapper(base) {
    val channelID = "channelID"
    val channelName = "Channel Name"
    private var notificationManager: NotificationManager? = null
init {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
    {
        createChannel()

    }
}

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val channel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH)
        channel.enableVibration(true)
        getManager().createNotificationChannel(channel)
    }

    fun getManager() : NotificationManager {
        if (notificationManager == null) notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return notificationManager as NotificationManager
    }
    fun getNotificationBuilder(): NotificationCompat.Builder {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle("Riminder For Food")
            .setContentText(mealName)
            .setSmallIcon(R.drawable.ic_alarm)
            .setColor(Color.YELLOW)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setAutoCancel(true)
    }

}