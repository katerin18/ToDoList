package com.example.todolist.dialogClass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.todolist.R
import java.util.*


class CalendDialog: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.dial_calendar, container, false)

        val butSave = rootView.findViewById<Button>(R.id.btnSave)
        val butCancel = rootView.findViewById<Button>(R.id.btnCancel)
        val txtDate = rootView.findViewById<TextView>(R.id.dateText)
        val datePicker = rootView.findViewById<DatePicker>(R.id.datePicker)
      //  val calendar = rootView.findViewById<CalendarView>(R.id.calView)

//        calendar.setOnDateChangeListener { calendarView, year, month, day ->
//            val a = "$day.${month+1}.$year"
//            txtDate.text = a
//        }

//        datePicker.setOnDateChangedListener{ datePicker, year, month, day ->
//            val a = "$day.${month+1}.$year"
//            txtDate.text = a
//        }

        butSave.setOnClickListener {
            // Put chose date to TextView
        }

        butCancel.setOnClickListener {
            dismiss()
        }

        return rootView
    }
}