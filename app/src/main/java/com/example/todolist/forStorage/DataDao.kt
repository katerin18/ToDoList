package com.example.todolist.forStorage

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao // data access object
interface DataDao {
    @Insert
    fun addPlan(item: RowPlan)

    @Query("SELECT * FROM data_creating ORDER BY id ASC")
    fun readAllData(): LiveData<List<RowPlan>>

    @Update
    fun updatePlan(item: RowPlan)

    @Delete
    fun deletePlan(item: RowPlan)
}