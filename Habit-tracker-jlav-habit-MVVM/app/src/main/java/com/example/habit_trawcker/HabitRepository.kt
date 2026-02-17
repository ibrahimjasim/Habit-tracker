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
}

