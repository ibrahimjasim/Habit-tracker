package com.example.habit_trawcker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HabitViewModelFactory(
    private val repository: HabitRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        return HabitViewModel(repository) as T
    }
}