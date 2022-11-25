package com.kr.birthdayreminderapp.helper

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.kr.birthdayreminderapp.R
import java.util.*

@BindingAdapter(value = ["capitalizeText"])
fun setCapitalizeText(view: TextView, smallText: String) {
    view.text = smallText.replaceFirstChar { it.uppercase() }
}

@BindingAdapter(value = ["birthDate"])
fun setBirthDate(view: TextView, birthDate: String) {
    try {
        view.text = String.format(
            Locale.ENGLISH,
            view.context.getString(R.string.birthdate_format),
            Utils.setDisplayDateFormat(birthDate)
        )
    }catch (e: Exception){
        e.printStackTrace()
    }
}

@BindingAdapter(value = ["contactNo"])
fun setContactNo(view: TextView,contactNo: String){
    if(contactNo.isNotEmpty()){
        try{
            view.text = String.format(
                Locale.ENGLISH,
                view.context.getString(R.string.contact_no_format),
                contactNo
            )
        }catch (e: Exception){
            view.visibility = View.GONE
            e.printStackTrace()
        }
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["emailAddress"])
fun setEmailAddress(view: TextView,emailAddress: String){
    if(emailAddress.isNotEmpty()){
        try{
            view.text = String.format(
                Locale.ENGLISH,
                view.context.getString(R.string.email_format),
                emailAddress
            )
        }catch (e: Exception){
            view.visibility = View.GONE
            e.printStackTrace()
        }
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["dobAge"])
fun setAge(view: TextView,dobAge: String){
    try {
        view.text = Utils.calculateAge(dobAge)
    }catch (e: Exception){
        e.printStackTrace()
    }
}