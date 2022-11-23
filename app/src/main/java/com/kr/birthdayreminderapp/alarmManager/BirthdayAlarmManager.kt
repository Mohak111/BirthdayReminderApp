package com.kr.birthdayreminderapp.alarmManager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.kr.birthdayreminderapp.helper.Constant

object BirthdayAlarmManager {

    fun getAlarmManager(context: Context): AlarmManager {
        return context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    fun getPendingIntent(context: Context?, flag: Int): PendingIntent? {
        val intent = Intent(context, AlarmReceiver::class.java)
        return PendingIntent.getBroadcast(context, Constant.DAILY_REMINDER_REQUEST_CODE, intent, flag)
    }
}