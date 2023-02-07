package com.example.todolist.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.ToDoItem
import com.example.todolist.ViewModel.DataViewModel
import com.example.todolist.adapter.toDoAdapter
import com.example.todolist.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainFragment : Fragment() {

    private lateinit var viewModel: DataViewModel
    lateinit var floatBut : FloatingActionButton
    lateinit var binding: ActivityMainBinding
    var adptr : toDoAdapter = toDoAdapter()

    var listPlan = arrayListOf<ToDoItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        floatBut = view.findViewById(R.id.btn_addPlan)
        floatBut.setOnClickListener { // Opening PCFragment
            val PCFragment = PlanCardFragment()
            val transaction : FragmentTransaction = childFragmentManager.beginTransaction()
            transaction.remove(this)
            transaction.add(R.id.mainFragment, PCFragment)
            transaction.commit()
            // так тоже можно:
//            val fragmentManager = (activity as FragmentActivity).supportFragmentManager
//            val transaction : FragmentTransaction = fragmentManager.beginTransaction()
        }

        return view
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}