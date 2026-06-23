package com.nauli.anmp_uts.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit")
data class Habit(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var title: String,
    var description: String,

    var progress: Int,
    var target: Int,

    var unit: String,
    var icon: Int
)