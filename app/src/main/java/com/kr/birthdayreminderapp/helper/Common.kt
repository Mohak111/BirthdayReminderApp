package com.kr.birthdayreminderapp.helper

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["capitalizeText"])
fun setCapitalizeText(view:TextView, smallText: String){
    view.text = smallText.replaceFirstChar { it.uppercase() }
}