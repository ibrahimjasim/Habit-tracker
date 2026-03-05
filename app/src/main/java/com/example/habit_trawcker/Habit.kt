package com.example.habit_trawcker

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.PropertyName

@Entity(tableName = "habits")
data class Habit(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String = "",
    val description: String = "",

    val createdDate: Long = System.currentTimeMillis(),

    val frequencyType: String = "daily",
    val targetPerPeriod: Int = 1,

    @get:PropertyName("isCompleted") @set:PropertyName("isCompleted")
    var isCompleted: Boolean = false,

    @get:PropertyName("isBad") @set:PropertyName("isBad")
    var isBad: Boolean = false,

)