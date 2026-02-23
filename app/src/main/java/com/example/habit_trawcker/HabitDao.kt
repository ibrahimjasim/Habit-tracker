package com.example.habit_trawcker

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habit: Habit): Long

    @Update
    suspend fun update(habit: Habit)

    @Delete
    suspend fun delete(habit: Habit)

    @Query("SELECT * FROM habits")
    suspend fun getAll(): List<Habit>

    @Query("SELECT * FROM habits ORDER BY createdDate DESC")
    fun getAllFlow(): Flow<List<Habit>>

    @Query("DELETE FROM habits")
    suspend fun clearAll()
}