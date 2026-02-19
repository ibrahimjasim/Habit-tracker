package com.example.habit_trawcker

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface HabitDao {

    @Insert
    suspend fun insert(habit: Habit)

    @Update
    suspend fun update(habit: Habit)

    @Delete
    suspend fun delete(habit: Habit)

    @Query("SELECT * FROM habits")
    suspend fun getAll(): List<Habit>

    @Query("SELECT * FROM habits ORDER BY createdDate DESC")
    fun getAllFlow(): Flow<List<Habit>>
}