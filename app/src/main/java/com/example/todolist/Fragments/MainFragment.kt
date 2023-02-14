package com.example.todolist.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.adapter.ToDoItem
import com.example.todolist.ViewModel.SharedDataVM
import com.example.todolist.adapter.toDoAdapter
import com.example.todolist.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainFragment : Fragment() {

    private lateinit var s_viewModel: SharedDataVM
    lateinit var floatBut: FloatingActionButton
    lateinit var binding: ActivityMainBinding
    lateinit var textNumb: TextView
  //  var adptr: toDoAdapter = toDoAdapter(this)

    var listPlan = arrayListOf<ToDoItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        floatBut = view.findViewById(R.id.btn_addPlan)
        textNumb = view.findViewById(R.id.numSuccess)
        floatBut.setOnClickListener { // Opening PCFragment

            val PCFragment = PlanCardFragment()
            val fragmentManager: FragmentManager = parentFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.mainFragment, PCFragment, "PlanCardFragment")
            transaction.addToBackStack("")
            transaction.commit()
        }
        s_viewModel = ViewModelProvider(this).get(SharedDataVM::class.java)

        s_viewModel.getData().observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "BYYYY", Toast.LENGTH_SHORT).show()
            // do something like UI update
            textNumb.text = it
            Log.d("mmm", it)
        }
    }
}