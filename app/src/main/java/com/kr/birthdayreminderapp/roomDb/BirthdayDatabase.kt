package com.kr.birthdayreminderapp.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kr.birthdayreminderapp.helper.Constant

@Database(entities = [BirthdateModel::class], version = 1)
abstract class BirthdayDatabase : RoomDatabase() {

    abstract fun birthDateDao(): BirthDateDao

    companion object {

        @Volatile
        private var INSTANCE: BirthdayDatabase? = null

        fun getDatabase(context: Context): BirthdayDatabase {
            if (INSTANCE == null) {
                try {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        BirthdayDatabase::class.java,
                        Constant.DB_NAME
                    ).build()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return INSTANCE as BirthdayDatabase
        }
    }
}