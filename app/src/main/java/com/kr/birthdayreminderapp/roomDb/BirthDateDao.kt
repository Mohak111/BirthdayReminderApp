package com.kr.birthdayreminderapp.roomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BirthDateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBirthDates(birthDateData: BirthdateModel)

    @Query("SELECT * FROM birthdateTable")
    fun getAllRecords(): MutableList<BirthdateModel>
}