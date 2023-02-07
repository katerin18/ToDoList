package com.example.todolist

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.ViewModel.DataViewModel
import com.example.todolist.ViewModel.PCFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*

class PlanCardUI : AppCompatActivity() {

    lateinit var btnClose: ImageButton
    lateinit var btnSave: Button
    lateinit var btnDel: Button
    lateinit var txtDate : TextView

    lateinit var choImp: RadioGroup
    lateinit var radImport: RadioButton
    lateinit var viewModel: DataViewModel
    var txtImportance: String = ""
    var txtDd : String = ""
    private var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_card_ui)

        var txtPlan: EditText = findViewById(R.id.inputPlan)
        choImp = findViewById(R.id.choiceIm)

        // to get Importance
        choImp.setOnCheckedChangeListener { group, checkedId ->
            radImport = findViewById(checkedId)
            txtImportance = radImport.text.toString()
        }

        // Closing window
        btnClose = findViewById(R.id.closeActivity)
        btnClose.setOnClickListener {}

        fun pressCancel(){}


        fun pressSave(){
            val dataSet = txtPlan.text.toString()+" "+txtImportance+" "+txtDd
            Log.d("VvModel", dataSet)
            viewModel = ViewModelProvider(this, PCFactory(application, dataSet))[DataViewModel::class.java]
        }

        fun showSaveDialog(){
            MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.action))
                .setMessage(getString(R.string.questionS))
                .setCancelable(false)
                .setNegativeButton(getString(R.string.cancel)) { _, _ ->
                    pressCancel()
                }
                .setPositiveButton(getString(R.string.yes)) { _, _ ->
                    pressSave()
                }
                .show()
        }

        // Saving plan
        btnSave = findViewById(R.id.butSave)
        btnSave.setOnClickListener {
            if(txtPlan.text.toString() == ""){
                if (txtImportance == ""){
                    Toast.makeText(this, "Input your plan & choose the importance, please.", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Input your plan, please.", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                showSaveDialog()
            }
        }

        fun pressDelete(){}

        fun showDelDialog(){
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.action)
                .setMessage(getString(R.string.questionD))
                .setCancelable(false)
                .setNegativeButton(getString(R.string.cancel)) { _, _ ->
                    pressCancel()
                }
                .setPositiveButton(getString(R.string.yes)) { _, _ ->
                    pressDelete()
                }
                .show()
        }
        // Deleting plan
        btnDel = findViewById(R.id.butDel)
        btnDel.setOnClickListener {
        }

        // Set the deadline
        txtDate = findViewById(R.id.txtDate)
        // Switch
        val standardSwitch: Switch = findViewById(R.id.deadSwitch)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        standardSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                val dpd = DatePickerDialog(this@PlanCardUI, { view, yearr, monthOfYear, dayOfMonth ->
                    cal.set(Calendar.YEAR, yearr)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val myFormat = "dd/MM/yyyy"
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    txtDate.text = sdf.format(cal.time)
                    txtDd = sdf.format(cal.time)

                }, year, month, day)
                dpd.setButton(
                    DialogInterface.BUTTON_NEGATIVE, "Cancel"){
                        dpd, which ->
                    run {
                        if (which == DialogInterface.BUTTON_NEGATIVE) {
                            dpd.dismiss()
                            standardSwitch.isChecked = false
                        }
                    }
                }
                dpd.show()
            }
            else{
                txtDate.text=""
            }
        }
    }
}
