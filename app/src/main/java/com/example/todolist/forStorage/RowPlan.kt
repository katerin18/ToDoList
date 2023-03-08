package com.example.todolist.forStorage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_creating")
data class RowPlan(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "plan_name")
    val plan_name: String,

    @ColumnInfo(name = "importance")
    val importance: String,

    @ColumnInfo(name = "deadline")
    val deadline: String
)