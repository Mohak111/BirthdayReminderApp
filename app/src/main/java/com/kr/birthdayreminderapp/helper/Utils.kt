package com.kr.birthdayreminderapp.helper

import android.os.SystemClock
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    private const val API_DATE_FORMAT = "yyyy-MM-dd"
    private const val DISPLAY_DATE_FORMAT = "dd MMMM, yyyy"
    private const val YEAR_FORMAT = "yyyy"
    private const val MONTH_FORMAT = "MM"
    private const val DAY_FORMAT = "dd"
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

    fun getDayFromDate(date: String): Int?{
        return try {
            var spf = SimpleDateFormat(API_DATE_FORMAT)
            val newDate = spf.parse(date)
            spf = SimpleDateFormat(DAY_FORMAT)
            Log.d("DAY",newDate?.let { spf.format(it) }.toString())
            newDate?.let { spf.format(it) }?.toInt()
        }catch (e: Exception){
            e.printStackTrace()
            0
        }
    }

    fun getMonthFromDate(date: String): Int?{
        return try {
            var spf = SimpleDateFormat(API_DATE_FORMAT)
            val newDate = spf.parse(date)
            spf = SimpleDateFormat(MONTH_FORMAT)
            Log.d("MONTH",newDate?.let { spf.format(it) }.toString())
            newDate?.let { spf.format(it) }?.toInt()
        }catch (e: Exception){
            e.printStackTrace()
            0
        }
    }

    fun getYearFromDate(date: String): Int?{
        return try {
            var spf = SimpleDateFormat(API_DATE_FORMAT)
            val newDate = spf.parse(date)
            spf = SimpleDateFormat(YEAR_FORMAT)
            Log.d("YEAR",newDate?.let { spf.format(it) }.toString())
            newDate?.let { spf.format(it) }?.toInt()
        }catch (e: Exception){
            e.printStackTrace()
            0
        }
    }

    fun handleSingleClick(): Boolean {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return false
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        return true
    }

    fun calculateAge(birthDate: String): String{
        val c = Calendar.getInstance()

        val day = getDayFromDate(birthDate)
        val month = getMonthFromDate(birthDate)
        val year = getYearFromDate(birthDate)

        c[Calendar.DAY_OF_MONTH]= day?:0
        c[Calendar.MONTH] = month?:0
        c[Calendar.YEAR] = year?:0
        c[Calendar.AM_PM] = 0
        c[Calendar.HOUR] = 8
        c[Calendar.MINUTE] = 0
        c[Calendar.SECOND] = 0
        c[Calendar.MILLISECOND] = 0

        val birthDateTimeInMillis = c.timeInMillis
        Log.d("BIRTHDATE_TIME_MILLIS",birthDateTimeInMillis.toString())
        val difference = Calendar.getInstance().timeInMillis - birthDateTimeInMillis
        return (difference/Constant.TIMES_IN_MILES_OF_YEAR).toString()
    }
}