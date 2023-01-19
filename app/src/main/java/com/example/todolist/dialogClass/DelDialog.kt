package com.example.todolist.dialogClass

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

import androidx.fragment.app.DialogFragment
import com.example.todolist.R

class DelDialog: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val rootView: View = inflater.inflate(R.layout.dial_frag_delete, container, false)

        val butYes = rootView.findViewById<Button>(R.id.btnYes)
        val butNo = rootView.findViewById<Button>(R.id.btnNo)

        butYes.setOnClickListener {
            // something
        }

        butNo.setOnClickListener {
            dismiss()
        }
        return rootView
    }

}