package com.example.habit_trawcker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HabitViewModel(
    private val repository: HabitRepository
) : ViewModel() {

    val habits = repository.allHabits

    fun addHabit(name: String, description: String, isBad: Boolean = false) {
        viewModelScope.launch {
            repository.syncFromFirestore()
            repository.addHabit(
                Habit(
                    name = name,
                    description = description,
                    isBad = isBad
                )
            )
        }
    }

    fun updateCompletion(habit: Habit, completed: Boolean) {
        viewModelScope.launch {
            repository.updateHabit(habit.copy(isCompleted = completed))
        }
    }

    fun updateHabit(habit: Habit) {
        viewModelScope.launch {
            repository.updateHabit(habit)
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            repository.deleteHabit(habit)
        }
    }

    fun syncAndClearHabits() {
        viewModelScope.launch {
            repository.syncAndClear()
        }
    }
}