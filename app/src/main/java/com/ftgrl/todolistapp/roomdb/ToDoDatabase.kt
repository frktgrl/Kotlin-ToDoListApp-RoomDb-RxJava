package com.ftgrl.todolistapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ftgrl.todolistapp.model.ToDo

@Database(entities = [ToDo::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

}