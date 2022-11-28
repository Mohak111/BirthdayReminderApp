package com.kr.birthdayreminderapp.helper

import android.app.Activity
import android.widget.Toast

object Validation {
    fun checkRecords(mActivity: Activity, userName: String, birthDate: String): Boolean{
        return if(userName.trim().isEmpty()){
            Toast.makeText(mActivity, "Please Enter Name", Toast.LENGTH_SHORT).show()
            false
        }else if(birthDate.trim().isEmpty()){
            Toast.makeText(mActivity, "Please Enter Birthdate", Toast.LENGTH_SHORT).show()
            false
        }else{
            true
        }
    }
}