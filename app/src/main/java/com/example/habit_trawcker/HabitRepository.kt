package com.example.habit_trawcker

import kotlinx.coroutines.flow.Flow

class HabitRepository(
    private val habitDao: HabitDao
) {

    val allHabits: Flow<List<Habit>> =
        habitDao.getAllFlow()

    suspend fun addHabit(habit: Habit) {
        habitDao.insert(habit)
    }

    suspend fun updateCompletion(id: Int, completed: Boolean) {
        habitDao.updateCompletion(id, completed)
    }

    suspend fun updateHabit(habit: Habit) {
        dao.update(habit)
    }

    suspend fun deleteHabit(habit: Habit) {
        dao.delete(habit)
    }
}
