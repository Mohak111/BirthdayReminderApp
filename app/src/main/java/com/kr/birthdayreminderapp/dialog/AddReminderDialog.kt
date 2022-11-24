package com.kr.birthdayreminderapp.dialog

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kr.birthdayreminderapp.R
import com.kr.birthdayreminderapp.databinding.DialogAddDetailsBinding
import com.kr.birthdayreminderapp.helper.Coroutines
import com.kr.birthdayreminderapp.helper.Utils
import com.kr.birthdayreminderapp.presenter.AddReminderPresenter
import com.kr.birthdayreminderapp.roomDb.BirthdateModel
import com.kr.birthdayreminderapp.roomDb.BirthdayDatabase
import java.util.*

class AddReminderDialog(private var mActivity: Activity,private var db: BirthdayDatabase): BottomSheetDialogFragment(),
    AddReminderPresenter {

    private lateinit var binding: DialogAddDetailsBinding
    private var selectedBirthdate: String = ""
    private var scheduleTime: Long = 0L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_details,container,false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialogWithSoftInputMode)
    }

    override fun onStart() {
        super.onStart()
        addReminderDialog = dialog
        if(dialog != null){
            addReminderDialog?.window?.setWindowAnimations(R.style.Slide)
            addReminderDialog?.setCanceledOnTouchOutside(true)
            addReminderDialog?.setCancelable(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.presenter = this
    }

    companion object{
        private const val TAG = "fullscreen_dialog"
        private var addReminderDialog: Dialog? = null

        fun display(fragmentManager: FragmentManager,db:BirthdayDatabase,context: Activity){
            val dialog = AddReminderDialog(context,db)
            dialog.show(fragmentManager, TAG)
        }
    }

    override fun onBirthDateSelectionClicked() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            mActivity, { _, selectedYear, selectedMonth, selectedDay ->
                c[Calendar.DAY_OF_MONTH]= selectedDay
                c[Calendar.MONTH] = selectedMonth
                c[Calendar.YEAR] = Utils.getCurrentYear() + 1
                c[Calendar.AM_PM] = 0
                c[Calendar.HOUR] = 8
                c[Calendar.MINUTE] = 0
                c[Calendar.SECOND] = 0
                c[Calendar.MILLISECOND] = 0
                binding.edtDob.setText(Utils.setDisplayDateFormat("$selectedYear-${selectedMonth + 1}-$selectedDay"))
                selectedBirthdate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                scheduleTime = c.timeInMillis
                Log.d("SELECTED_TIME",c.timeInMillis.toString())
            }, year, month, day
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    override fun onSaveClicked() {
        Coroutines.ioThenMain({
            val birthDateData = BirthdateModel()
            birthDateData.userName = binding.edtName.text.toString()
            birthDateData.birthDate = selectedBirthdate
            birthDateData.scheduleTime = scheduleTime
            birthDateData.email = binding.edtEmailId.text.toString()
            birthDateData.contactNumber = binding.edtContactNo.text.toString()
            db.birthDateDao().insertBirthDates(birthDateData)
        },{
            Toast.makeText(mActivity, "Record Inserted Successfully", Toast.LENGTH_SHORT).show()
            addReminderDialog?.dismiss()
        })
    }
}