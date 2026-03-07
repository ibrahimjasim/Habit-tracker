package com.example.habit_trawcker

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.habit_trawcker.databinding.ItemHabitBinding

class HabitAdapter(
    private val onEditClick: (Habit) -> Unit,
    private val onDeleteClick: (Habit) -> Unit,
    private val onCheckedChange: (Habit, Boolean) -> Unit
) : ListAdapter<Habit, HabitAdapter.HabitViewHolder>(HabitDiffCallback()) {

    /*class HabitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.habitName)
        val description: TextView = view.findViewById(R.id.habitDescription)
        val btnEdit: ImageButton = view.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
        val checkCompleted: CheckBox = view.findViewById(R.id.checkCompleted)
    } */

    class HabitViewHolder(
        val binding: ItemHabitBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {

        val binding = ItemHabitBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {

        //val item = getItem(position)
        //val habit = item.habit
        val habit = getItem(position)
        val binding = holder.binding

        binding.habitName.text = habit.name
        binding.habitDescription.text = habit.description
        //binding.streakText.text = "${item.streak} days"

        binding.btnEdit.setOnClickListener { onEditClick(habit) }
        binding.btnDelete.setOnClickListener { onDeleteClick(habit) }

        binding.checkCompleted.setOnCheckedChangeListener(null)
        binding.checkCompleted.isChecked = habit.isCompleted

        binding.checkCompleted.setOnCheckedChangeListener { _, isChecked ->
            onCheckedChange(habit, isChecked)
        }

        if (habit.isCompleted) {
            binding.habitName.paintFlags =
                binding.habitName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            binding.habitName.paintFlags =
                binding.habitName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }


    class HabitDiffCallback : DiffUtil.ItemCallback<Habit>() {
        override fun areItemsTheSame(oldItem: Habit, newItem: Habit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean {
            return oldItem == newItem
        }
    }
}