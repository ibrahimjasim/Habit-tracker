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
import com.example.habit_trawcker.databinding.ActivityMainBinding
import com.example.habit_trawcker.databinding.DialogAddHabitBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // enable viewBindning
    private lateinit var adapter: HabitAdapter
    private lateinit var viewModel: HabitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)    tas bort enable viewBindning

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // val recycler = findViewById<RecyclerView>(R.id.habitRecyclerView)
        //val addHabitBtn = findViewById<MaterialButton>(R.id.addHabitFab)
        //val logoutBtn = findViewById<MaterialButton>(R.id.btnLogout)

        val recycler = binding.habitRecyclerView
        val addHabitBtn = binding.addHabitFab
        val logoutBtn = binding.btnLogout

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
            },
            onCheckedChange = { habit, isChecked ->
                viewModel.updateCompletion(habit, isChecked)
            }
        )

        //recycler.layoutManager = LinearLayoutManager(this)
        //recycler.adapter = adapter

        binding.habitRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.habitRecyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.habits.collect {
                adapter.submitList(it)
            }
        }

        binding.addHabitFab.setOnClickListener {   //binding.addHabitBtn
            showAddHabitDialog()
        }

        binding.btnLogout.setOnClickListener {   // binding.logoutBtn
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showAddHabitDialog() {

        val dialogBinding = DialogAddHabitBinding.inflate(layoutInflater)
        //val view = layoutInflater.inflate(R.layout.dialog_add_habit, null)
        //val nameInput = view.findViewById<EditText>(R.id.inputName)
        //val descInput = view.findViewById<EditText>(R.id.inputDescription)

        AlertDialog.Builder(this)
            .setTitle("Add Habit")
            .setView(dialogBinding.root)  // view
            .setPositiveButton("Add") { _, _ ->
                //val name = nameInput.text.toString()
                val name = dialogBinding.inputName.text.toString()
                val desc = dialogBinding.inputDescription.text.toString()
                if (name.isNotEmpty()) {
                    viewModel.addHabit(
                        name,
                        desc
                    )
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showEditHabitDialog(habit: Habit) {
        val dialogBinding = DialogAddHabitBinding.inflate(layoutInflater)

        //val view = layoutInflater.inflate(R.layout.dialog_add_habit, null)
        //val nameInput = view.findViewById<EditText>(R.id.inputName)
        //val descInput = view.findViewById<EditText>(R.id.inputDescription)

        dialogBinding.inputName.setText(habit.name)
        dialogBinding.inputDescription.setText(habit.description)

        //nameInput.setText(habit.name)
        //descInput.setText(habit.description)

        AlertDialog.Builder(this)
            .setTitle("Edit Habit")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") { _, _ ->
                val updatedHabit = habit.copy(
                    name = dialogBinding.inputName.text.toString(),
                    description = dialogBinding.inputDescription.text.toString()
                )
                viewModel.updateHabit(updatedHabit)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}