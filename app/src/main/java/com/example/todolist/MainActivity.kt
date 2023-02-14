package com.example.todolist

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.Fragments.PlanCardFragment
import com.example.todolist.adapter.ToDoItem
import com.example.todolist.adapter.toDoAdapter
import com.example.todolist.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), DataPass, toDoAdapter.Listener {
    lateinit var binding: ActivityMainBinding
    var adptr: toDoAdapter = toDoAdapter(this)

    lateinit var floatBut: FloatingActionButton
    lateinit var textNumb: TextView
    val PCFragment = PlanCardFragment()

    var listPlan = arrayListOf<ToDoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //  setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        floatBut = findViewById(R.id.btn_addPlan)
        textNumb = findViewById(R.id.numSuccess)
        floatBut.setOnClickListener { // Opening PCFragment

            val fragmentManager: FragmentManager = supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.mainFragment, PCFragment, "PlanCardFragment")
            transaction.addToBackStack("")
            transaction.commit()
        }
    }

    override fun dataPass(data: String) {
        val a: ArrayList<String> = data.split(';') as ArrayList<String>
        init(a[0], a[1], a[2])
    }

    private fun init(plan: String, deadline: String, imprtnc: String){
        binding.apply {
            recView.layoutManager = LinearLayoutManager(this@MainActivity)
            recView.adapter = adptr
            val todo = ToDoItem(plan, deadline, imprtnc)
            adptr.addToDo(todo)
        }
    }


    override fun onChecked(plan: ToDoItem) {
        Toast.makeText(this, "plan ps ${plan.plan}", Toast.LENGTH_SHORT).show()
    }

}