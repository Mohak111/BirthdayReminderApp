package com.kr.birthdayreminderapp.roomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BirthDateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBirthDates(birthDateData: BirthdateModel)

    @Query("SELECT * FROM birthdateTable WHERE recordDeleted = 0")
    fun getAllRecords(): MutableList<BirthdateModel>

    @Query("UPDATE birthdateTable SET userName=:userName,birthDate=:birthDate,contactNumber=:contactNumber,emailId=:email,scheduleTime=:scheduleTime WHERE id=:id")
    fun updateRecord(id: Int, userName: String, birthDate: String, contactNumber: String, email: String, scheduleTime: Long)

    @Query("UPDATE birthdateTable SET recordDeleted = 1 WHERE id=:id")
    fun deleteRecord(id: Int)
}