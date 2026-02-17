package com.example.habit_trawcker

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import androidx.appcompat.app.AlertDialog
import kotlin.toString

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HabitAdapter
    private lateinit var viewModel: HabitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.habitRecyclerView)
        val fab = findViewById<FloatingActionButton>(R.id.addHabitFab)

        adapter = HabitAdapter(mutableListOf())
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

        fab.setOnClickListener {
            showAddHabitDialog()
        }


    }

    private fun showAddHabitDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_add_habit, null)

        val nameInput = view.findViewById<EditText>(R.id.habitName)
        val descInput = view.findViewById<EditText>(R.id.habitDescription)

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