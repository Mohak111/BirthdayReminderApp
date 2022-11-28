package com.kr.birthdayreminderapp.roomDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "birthdateTable")
open class BirthdateModel: Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "userName")
    var userName: String = ""

    @ColumnInfo(name = "birthDate")
    var birthDate: String = ""

    @ColumnInfo(name = "contactNumber")
    var contactNumber: String = ""

    @ColumnInfo(name = "emailId")
    var email: String = ""

    @ColumnInfo(name = "scheduleTime")
    var scheduleTime: Long = 0L

    @ColumnInfo(name = "recordDeleted")
    var recordDeleted = 0
}