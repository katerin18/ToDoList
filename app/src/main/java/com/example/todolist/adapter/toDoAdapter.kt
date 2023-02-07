package com.example.todolist.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.ToDoItem
import com.example.todolist.databinding.ItemPlanBinding

// адаптер - посредник между рекуклером, шаблоном и данными
class toDoAdapter: RecyclerView.Adapter<toDoAdapter.toDoViewHolder>() {

    var toDoList = ArrayList<ToDoItem>()
    private var size_ = 1

    class toDoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemPlanBinding.bind(view)
        val titlePlan: TextView = view.findViewById(R.id.txtPlan)
        fun bind(toDo: ToDoItem) = with(binding){
            txtPlan.text = toDo.plan
        }
    }

    // создает класс toDoViewHolder и "надувает"(inflate) разметку в recycler
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): toDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plan, parent, false)
        return toDoViewHolder(view)
    }

    override fun onBindViewHolder(holder: toDoViewHolder, position: Int) {
        holder.bind(toDoList[position])
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    fun addToDo(toDo: ToDoItem){
        toDoList.add(toDo)
        notifyDataSetChanged()
    }
//     fun setFields(holder: toDoViewHolder, position: Int, items: List<ToDoItem>?){
//        if(items != null){
//            size_ = items.size
//            holder.titlePlan.text = items.get(position).plan
//        }
//    }

}