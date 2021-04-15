package com.example.navia.broadCast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.navia.notification.MealNotification

class AlarmBroadCast: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val mealName = intent?.getStringExtra("foodName")
        val notificationUtils = MealNotification(context!!, mealName!!)
        val notification = notificationUtils.getNotificationBuilder().build()
        notificationUtils.getManager().notify(System.currentTimeMillis().toInt(), notification)

    }
}