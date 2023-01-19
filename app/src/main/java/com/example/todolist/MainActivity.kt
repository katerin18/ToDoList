package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.adapter.toDoAdapter
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.dialogClass.SaveDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var floatBut : FloatingActionButton
    lateinit var binding: ActivityMainBinding
    var adptr : toDoAdapter = toDoAdapter()
    var listPlan = arrayListOf<ToDoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adding a plan
        floatBut = findViewById(R.id.btn_addPlan)
        floatBut.setOnClickListener {
            val intent = Intent(this@MainActivity, PlanCardUI::class.java)
            startActivity(intent)
        }
        init()
    }

    private fun init(){
        binding.apply {
            recView.layoutManager = LinearLayoutManager(this@MainActivity)
            recView.adapter = adptr
//            adptr.addToDo(SaveDialog().a)
//            listPlan.add(SaveDialog().a)
        }
    }


}