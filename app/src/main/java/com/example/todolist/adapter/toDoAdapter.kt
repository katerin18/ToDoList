package com.example.todolist.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.ItemPlanBinding


// адаптер - посредник между рекуклером, шаблоном и данными
class toDoAdapter(val listener: Listener): RecyclerView.Adapter<toDoAdapter.toDoViewHolder>() {

    var toDoList = ArrayList<ToDoItem>()
    var checkedToDo = ArrayList<ToDoItem>()

    class toDoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemPlanBinding.bind(view)

        @SuppressLint("ResourceAsColor")
        fun bind(toDo: ToDoItem, listener: Listener) = with(binding){
            txtPlan.text = toDo.plan
            txtDdPlan.text = toDo.dd

            when(toDo.imp){
                "Low" -> txtDdPlan.setTextColor(Color.parseColor("#509369"))
                "Medium" -> txtDdPlan.setTextColor(Color.BLACK)
                "High" -> txtDdPlan.setTextColor(Color.parseColor("#E66B6B"))
            }

            itemView.setOnClickListener {
                listener.onChecked(toDo)
            }
        }
    }

    // создает класс toDoViewHolder и "надувает"(inflate) разметку в recycler
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): toDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plan, parent, false)

        return toDoViewHolder(view)
    }

    // заполнение
    @SuppressLint("CutPasteId")
    override fun onBindViewHolder(holder: toDoViewHolder, position: Int) {
        holder.bind(toDoList[position], listener)

        val myCheckBox = holder.itemView.findViewById(R.id.checkPlan) as CheckBox
        val myPlan = holder.itemView.findViewById(R.id.txtPlan) as TextView

        // adding a completed plan
        myCheckBox.setOnCheckedChangeListener { _, _ ->
            val plan = toDoList[position]

            if(myCheckBox.isChecked){
                checkedToDo.add(plan)
                myPlan.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                listener.onPress(checkedToDo.size)
            }
            if(!myCheckBox.isChecked){
                checkedToDo.remove(plan)
                myPlan.paintFlags = Paint.LINEAR_TEXT_FLAG
                listener.onPress(checkedToDo.size)
            }
        }
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addToDo(plan: ToDoItem){
        toDoList.add(plan)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onChecked(plan: ToDoItem){}
        fun onPress(suc: Int){}
    }

}