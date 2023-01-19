package com.example.todolist.dialogClass

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.todolist.R

class SaveDialog : DialogFragment() {
    //var plan = requireArguments().getInt("num")
    var plan : String = ""
    var imprtnc : String = ""
    var deadline : String = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.dial_frag_save, container, false)

        val butYes = rootView.findViewById<Button>(R.id.btnYes)
        val butCancel = rootView.findViewById<Button>(R.id.btnCancel)

        butYes.setOnClickListener {
            arguments?.getString("plan")?.let {
                plan = it
            }
            arguments?.getString("importance")?.let {
                imprtnc = it
            }
            arguments?.getString("deadline")?.let {
                deadline = it
            }
            Log.d("This is a SvaDialog", plan)
            Log.d("This is a SvaDialog", imprtnc)
            Log.d("This is a SvaDialog", deadline)
            dismiss()
            // Saving a plan
            // Достать инфу из полей, записать в переменные
            // Вызвать конструктор DataClass
        }

        butCancel.setOnClickListener {
            dismiss()
        }

        return rootView
    }
}