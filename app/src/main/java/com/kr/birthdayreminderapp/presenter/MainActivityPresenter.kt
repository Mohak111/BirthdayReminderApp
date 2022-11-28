package com.kr.birthdayreminderapp.presenter

import com.kr.birthdayreminderapp.roomDb.BirthdateModel

interface MainActivityPresenter {
    fun onAddClicked()
    fun callbackSuccessfullyRecordStored()
    fun onEditClicked(data: BirthdateModel)
}