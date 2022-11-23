package com.kr.birthdayreminderapp.alarmManager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.kr.birthdayreminderapp.R
import com.kr.birthdayreminderapp.activities.MainActivity

class AlarmReceiver : BroadcastReceiver() {

    private var mNotificationManager: NotificationManager? = null
    private val NOTIFICATION_CHANNEL_REMINDER = "channel_reminder"
    private val NOTIFICATION_ID = 123

    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("RECEIVER","ALARM RECEIVER")
        setUpNotification(context)
    }

    private fun setUpNotification(context: Context) {
        mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel: NotificationChannel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_REMINDER,"Notification channel name", NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            mNotificationManager?.createNotificationChannel(notificationChannel)
        }
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_REMINDER)
                .setContentTitle("Reminder")
                .setContentText("Happy Birthday")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
        mNotificationManager?.notify(NOTIFICATION_ID, builder.build())
    }
}