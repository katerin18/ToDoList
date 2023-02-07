package com.example.todolist.ViewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class DataViewModel(application: Application, textPlan:String): AndroidViewModel(application) {

    private val _plan = MutableLiveData<String>()
    val plan : LiveData<String> = _plan

    fun savePlan(newPlan: String){
        _plan.value = newPlan
    }

    init {
        Toast.makeText(getApplication(), textPlan, Toast.LENGTH_SHORT).show()
    }

}