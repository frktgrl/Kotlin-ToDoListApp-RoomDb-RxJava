package com.ftgrl.todolistapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ToDo (
    @ColumnInfo(name="name")
    var name: String,

    @ColumnInfo(name="date")
    var date: String,

    @ColumnInfo(name="hour")
    var hour: Double)

{
    @PrimaryKey(autoGenerate = true)
    var id = 0
}

