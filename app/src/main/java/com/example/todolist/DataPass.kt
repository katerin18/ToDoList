package com.example.todolist

import com.example.todolist.adapter.ToDoItem

interface DataPass { // for transfer data between Activity and Fragment
    fun dataPass(data: ToDoItem)
}