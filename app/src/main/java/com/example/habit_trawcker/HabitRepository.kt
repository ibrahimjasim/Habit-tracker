package com.example.habit_trawcker

import kotlinx.coroutines.flow.Flow

class HabitRepository(
    private val dao: HabitDao
) {

    val allHabits: Flow<List<Habit>> =
        dao.getAllFlow()

    suspend fun addHabit(habit: Habit) {
        dao.insert(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        dao.update(habit)
    }

    suspend fun deleteHabit(habit: Habit) {
        dao.delete(habit)
    }
}
