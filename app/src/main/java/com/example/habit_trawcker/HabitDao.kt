package com.example.habit_trawcker

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface HabitDao {

    // ADD Habit and display
    @Insert
    suspend fun insert(habit: Habit)

    @Query("SELECT * FROM habits")
    suspend fun getAll(): List<Habit>

    @Query("SELECT * FROM habits ORDER BY createdDate DESC")
    fun getAllFlow(): Flow<List<Habit>>

    // Added Mark as completed
    @Query("UPDATE habits SET isCompleted = :completed WHERE id = :id")
    suspend fun updateCompletion(id: Int, completed: Boolean)


}