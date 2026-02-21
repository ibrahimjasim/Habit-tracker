package com.example.habit_trawcker

import android.content.Intent
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
        val addHabitBtn = findViewById<MaterialButton>(R.id.addHabitFab)
        val logoutBtn = findViewById<MaterialButton>(R.id.btnLogout)

        val dao = HabitDatabase.getDatabase(this).habitDao()
        val repo = HabitRepository(dao)

        viewModel = ViewModelProvider(
            this,
            HabitViewModelFactory(repo)
        )[HabitViewModel::class.java]


        adapter = HabitAdapter(
            onEditClick = { habit ->
                showEditHabitDialog(habit)
            },
            onDeleteClick = { habit ->
                viewModel.deleteHabit(habit)
            }
        )

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        lifecycleScope.launch {
            viewModel.habits.collect {
                // Använd submitList istället för updateList
                adapter.submitList(it)
            }
        }

        addHabitBtn.setOnClickListener {
            showAddHabitDialog()
        }

        logoutBtn.setOnClickListener {
            // Logga ut genom att gå tillbaka till LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
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
                val name = nameInput.text.toString()
                if (name.isNotEmpty()) {
                    viewModel.addHabit(
                        name,
                        descInput.text.toString()
                    )
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showEditHabitDialog(habit: Habit) {
        val view = layoutInflater.inflate(R.layout.dialog_add_habit, null)
        val nameInput = view.findViewById<EditText>(R.id.inputName)
        val descInput = view.findViewById<EditText>(R.id.inputDescription)

        nameInput.setText(habit.name)
        descInput.setText(habit.description)

        AlertDialog.Builder(this)
            .setTitle("Edit Habit")
            .setView(view)
            .setPositiveButton("Save") { _, _ ->
                val updatedHabit = habit.copy(
                    name = nameInput.text.toString(),
                    description = descInput.text.toString()
                )
                viewModel.updateHabit(updatedHabit)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
