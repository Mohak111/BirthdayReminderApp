package com.kr.birthdayreminderapp.helper

import android.os.SystemClock
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    const val API_DATE_FORMAT = "yyyy-MM-dd"
    const val DISPLAY_DATE_FORMAT = "dd MMMM, yyyy"
    private var mLastClickTime: Long = 0

    fun setDisplayDateFormat(date: String): String{
        return try {
            var spf = SimpleDateFormat(API_DATE_FORMAT)
            val newDate = spf.parse(date)
            spf = SimpleDateFormat(DISPLAY_DATE_FORMAT)
            newDate?.let { spf.format(it) }.toString()
        }catch (e: Exception){
            e.printStackTrace()
            ""
        }
    }

    fun getCurrentYear(): Int {
        return Calendar.getInstance().get(Calendar.YEAR)
    }

    fun handleSingleClick(): Boolean {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return false
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        return true
    }
}