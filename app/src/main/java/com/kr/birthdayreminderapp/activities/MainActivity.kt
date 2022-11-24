package com.kr.birthdayreminderapp.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kr.birthdayreminderapp.R
import com.kr.birthdayreminderapp.alarmManager.AlarmReceiver
import com.kr.birthdayreminderapp.alarmManager.BirthdayAlarmManager
import com.kr.birthdayreminderapp.databinding.ActivityMainBinding
import com.kr.birthdayreminderapp.dialog.AddReminderDialog
import com.kr.birthdayreminderapp.helper.Utils
import com.kr.birthdayreminderapp.presenter.MainActivityPresenter
import com.kr.birthdayreminderapp.roomDb.BirthdayDatabase

class MainActivity : AppCompatActivity(), MainActivityPresenter {

    private lateinit var binding: ActivityMainBinding
    private var alarmManager: AlarmManager? = null
    private var mIntent: Intent? = null
    private lateinit var db: BirthdayDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar.topAppBar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.presenter = this
        db = BirthdayDatabase.getDatabase(this)

        alarmManager = BirthdayAlarmManager.getAlarmManager(this)
        mIntent = Intent(applicationContext, AlarmReceiver::class.java)

//        setReminder()
    }

    private fun setReminder(){
        val notificationScheduleTime = System.currentTimeMillis() + 120000
        Log.d("NOTIFICATION_SCHEDULE",notificationScheduleTime.toString())
        alarmManager?.setExact(
            AlarmManager.RTC_WAKEUP,
            notificationScheduleTime,
            BirthdayAlarmManager.getPendingIntent(this,PendingIntent.FLAG_UPDATE_CURRENT)
        )
    }

    override fun onAddClicked() {
        if(Utils.handleSingleClick()) {
            AddReminderDialog.display(supportFragmentManager,db,this)
        }
    }
}