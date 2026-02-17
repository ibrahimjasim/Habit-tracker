package com.example.habit_trawcker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HabitViewModel(
    private val repository: HabitRepository
) : ViewModel() {

    val habits = repository.allHabits

    fun addHabit(name: String, description: String) {
        viewModelScope.launch {
            repository.addHabit(
                Habit(
                    name = name,
                    description = description
                )
            )
        }
    }
}