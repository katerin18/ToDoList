package com.example.todolist.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class DataViewModel(application: Application): AndroidViewModel(application) {

    val date = MutableLiveData<String>()

    fun setDate(n_date: String){
        date.value = n_date
    }

    fun getDate(): LiveData<String>{
        return date
    }

}