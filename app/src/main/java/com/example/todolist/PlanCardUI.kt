package com.example.todolist

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.dialogClass.DelDialog
import com.example.todolist.dialogClass.SaveDialog
import java.text.SimpleDateFormat
import java.util.*

class PlanCardUI : AppCompatActivity() {

    lateinit var btnClose: ImageButton
    lateinit var btnSave: Button
    lateinit var btnDel: Button
    lateinit var txtDate : TextView
    lateinit var txtPlan: EditText
    lateinit var choImp: RadioGroup
    lateinit var radImport: RadioButton
    var txtImportance: String = ""
    var txtDd : String = ""
    private var cal = Calendar.getInstance()

    @SuppressLint("ResourceType", "UseSwitchCompatOrMaterialCode")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_card_ui)

        txtPlan = findViewById(R.id.inputPlan)
        choImp = findViewById(R.id.choiceIm)

        // to get Importance
        choImp.setOnCheckedChangeListener { group, checkedId ->
            radImport = findViewById(checkedId)
            txtImportance = radImport.text.toString()
        }

        // Closing window
        btnClose = findViewById(R.id.closeActivity)
        btnClose.setOnClickListener {
            val intent = Intent(this@PlanCardUI, MainActivity::class.java)
            startActivity(intent)
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
                val dialog = SaveDialog()
                var args = Bundle()

                Log.d("This is data 1: ", txtPlan.text.toString())
                Log.d("This is data 2: ", radImport.text.toString())
                Log.d("This is data 3: ", txtDd)

                // packing data to Dialog
                args = Bundle().apply {
                    putString("plan", txtPlan.text.toString())
                    putString("importance", radImport.text.toString())
                    putString("deadline", txtDd)
                }
                dialog.arguments = args
                dialog.show(supportFragmentManager, "saveDialog")
            }
        }

        // Deleting plan
        btnDel = findViewById(R.id.butDel)
        btnDel.setOnClickListener {
            val dialog = DelDialog()
            dialog.show(supportFragmentManager, "deleteDialog")
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