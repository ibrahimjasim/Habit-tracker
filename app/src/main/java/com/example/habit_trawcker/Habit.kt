package com.example.habit_trawcker

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val description: String,

    val createdDate: Long = System.currentTimeMillis(),

    val frequencyType: String = "daily",
    val targetPerPeriod: Int = 1,

    val isCompleted: Boolean = false   // NY

)