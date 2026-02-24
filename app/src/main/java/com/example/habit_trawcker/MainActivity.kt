package com.example.habit_trawcker

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HabitAdapter
    private lateinit var viewModel: HabitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.habitRecyclerView)
        val addButton = findViewById<MaterialButton>(R.id.addHabitButton)

        //adapter = HabitAdapter(mutableListOf())
        adapter = HabitAdapter(mutableListOf()) { habit, isChecked ->
            viewModel.updateCompletion(habit, isChecked)
        }

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        val dao = HabitDatabase.getDatabase(this).habitDao()
        val repo = HabitRepository(dao)

        viewModel = ViewModelProvider(
            this,
            HabitViewModelFactory(repo)
        )[HabitViewModel::class.java]

        lifecycleScope.launch {
            viewModel.habits.collect {
                adapter.updateList(it)
            }
        }

        addButton.setOnClickListener {
            showAddHabitDialog()
        }


    }

    private fun showAddHabitDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_add_habit, null)

        val nameInput = view.findViewById<EditText>(R.id.inputName)
        val descInput = view.findViewById<EditText>(R.id.inputDescription)

        AlertDialog.Builder(this)
            .setTitle("Add Habit")
            .setView(view)
            .setPositiveButton("Add") { _, _ ->
                viewModel.addHabit(
                    nameInput.text.toString(),
                    descInput.text.toString()
                )
            }
            .setNegativeButton("Cancel", null)
            .show()
    }


}