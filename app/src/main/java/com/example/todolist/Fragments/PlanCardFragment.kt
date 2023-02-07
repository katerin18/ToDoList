package com.example.todolist.Fragments

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.ViewModel.DataViewModel
import com.example.todolist.ViewModel.PCFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*

class PlanCardFragment : Fragment() {

    lateinit var btnClose: ImageButton

    lateinit var txtPlan: EditText
    lateinit var choImp: RadioGroup
    lateinit var radImport: RadioButton

    lateinit var txtDate : TextView
    lateinit var btnSave: Button
    lateinit var btnDel: Button

    lateinit var viewModel: DataViewModel
    var txtImportance: String = ""
    var txtDd : String = ""
    private var cal = Calendar.getInstance()

//    companion object {
//        fun newInstance() = PlanCardFragment()
//    }

    override fun onCreateView( // про отображение элементов
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val fragmentManager: FragmentManager = supportFragmentManager
//        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        val view: View = inflater.inflate(R.layout.fragment_plan_card, container, false)
        txtPlan = view.findViewById(R.id.inputPlan)
        choImp = view.findViewById(R.id.choiceIm)
        btnSave = view.findViewById(R.id.butSave)
        btnDel = view.findViewById(R.id.butDel)
        btnClose = view.findViewById(R.id.closeActivity)
        choImp = view.findViewById(R.id.choiceIm)

        // to get Importance
        choImp.setOnCheckedChangeListener { group, checkedId ->
            radImport = view.findViewById(checkedId)
            txtImportance = radImport.text.toString()
        }

        // Closing window
        btnClose = view.findViewById(R.id.closeActivity)
        btnClose.setOnClickListener {
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

        return view
    }

    private fun pressCancel(){}

    private fun pressDelete(){}

    private fun pressSave(){
        val dataSet = txtPlan.text.toString()+" "+txtImportance+" "+txtDd
        Log.d("VvModel", dataSet)
        viewModel = ViewModelProvider(this, PCFactory(application = Application(), dataSet))[DataViewModel::class.java]
    }

    fun showSaveDialog(){
        MaterialAlertDialogBuilder(requireContext())
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

    fun showDeleteDialog(){
        MaterialAlertDialogBuilder(requireContext())
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
      //  viewModel = ViewModelProvider(this).get(PlanCardViewModel::class.java)

    }

}