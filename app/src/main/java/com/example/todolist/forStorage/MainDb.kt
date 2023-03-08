package com.example.todolist.forStorage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RowPlan::class], version = 1)
abstract class MainDb : RoomDatabase(){

    abstract fun getPlanDao(): DataDao

    companion object{ // as static in Java
        @Volatile
        private var INSTANCE : MainDb? = null

        fun getDb(context: Context): MainDb{
            val curInstance = INSTANCE

            if(curInstance != null){
                return curInstance
            }
            synchronized(this){ // защита от выполнения несколькими потоками
                return Room.databaseBuilder(
                    context.applicationContext,
                    MainDb::class.java,
                    "PlanDatabase"
                ).build()
            }
        }
    }
}