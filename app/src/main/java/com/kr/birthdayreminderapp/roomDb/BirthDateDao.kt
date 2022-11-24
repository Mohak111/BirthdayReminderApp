package com.kr.birthdayreminderapp.roomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface BirthDateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBirthDates(birthDateData: BirthdateModel)
}