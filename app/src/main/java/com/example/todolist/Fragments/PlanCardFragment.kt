package com.example.todolist.Fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.DataPass
import com.example.todolist.R
import com.example.todolist.ViewModel.DataViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*

class PlanCardFragment : Fragment() {

    lateinit var btnClose: ImageButton

    lateinit var txtPlan: EditText
    lateinit var choImp: RadioGroup
    lateinit var chLow: RadioButton
    lateinit var chMed: RadioButton
    lateinit var chHigh: RadioButton
    lateinit var radImport: RadioButton

    lateinit var txtDate : TextView
    lateinit var btnSave: Button
    lateinit var btnDel: Button

    lateinit var standardSwitch: Switch

    private lateinit var viewModel: DataViewModel
    var txtImportance: String = ""
    private var cal = Calendar.getInstance()

    lateinit var dataPasser: DataPass // for interface connect

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as DataPass // говорим активити смотреть интерфейс
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView( // про отображение элементов
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_plan_card, container, false)

        txtPlan = view.findViewById(R.id.inputPlan)
        choImp = view.findViewById(R.id.choiceIm)
        txtDate = view.findViewById(R.id.txtDate)
        btnSave = view.findViewById(R.id.butSave)
        btnDel = view.findViewById(R.id.butDel)
        btnClose = view.findViewById(R.id.closeActivity)

        chLow = view.findViewById(R.id.low)
        chMed = view.findViewById(R.id.med)
        chHigh = view.findViewById(R.id.high)

        standardSwitch = view.findViewById(R.id.deadSwitch)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)

        // to get Importance
        choImp.setOnCheckedChangeListener { group, checkedId ->
            radImport = view.findViewById(checkedId)
            txtImportance = radImport.text.toString()
        }

        // Closing window
        btnClose = view.findViewById(R.id.closeActivity)
        btnClose.setOnClickListener {
            // обработать случаи - когда пользователь редактирует дело и
            val fm = activity?.supportFragmentManager
            fm?.popBackStack("PCFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        // Saving the plan
        btnSave.setOnClickListener {
            if(txtPlan.text.toString() == ""){
                if (txtImportance == ""){
                    Toast.makeText(requireContext(), "Input your plan & choose the importance, please.", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(requireContext(), "Input your plan, please.", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                showSaveDialog()
            }
        }

        // Deleting the plan
        btnDel.setOnClickListener {
            showDeleteDialog()
        }

        // choosing importance
        standardSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                val dpd = DatePickerDialog(requireContext(), { view, yearr, monthOfYear, dayOfMonth ->
                    cal.set(Calendar.YEAR, yearr)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val myFormat = "dd/MM/yyyy"
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    viewModel.setDate(sdf.format(cal.time))
                    viewModel.getDate().observe(viewLifecycleOwner) {
                        txtDate.text = it
                    }


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

        return view
    }
    private fun pressDelete(){
        //listPlan.remove
        activity?.supportFragmentManager?.popBackStack()
    }

    private fun pressSave(){
        val dataSet = txtPlan.text.toString()+";"+txtImportance+";"+txtDate.text
        dataPasser.dataPass(dataSet)
        activity?.supportFragmentManager?.popBackStack()
       // activity?.onBackPressed()

        txtPlan.text.clear()
        txtDate.text = ""
        standardSwitch.isChecked = false

        chLow.isChecked = false
        chMed.isChecked = false
        chHigh.isChecked = false
    }

    fun showSaveDialog(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.action))
            .setMessage(getString(R.string.questionS))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                pressSave()
            }
            .show()
    }

    fun showDeleteDialog(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.action)
            .setMessage(getString(R.string.questionD))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                pressDelete()
            }
            .show()
    }


}