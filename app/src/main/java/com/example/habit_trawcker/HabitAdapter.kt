package com.example.habit_trawcker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class HabitAdapter(
    private val onEditClick: (Habit) -> Unit,
    private val onDeleteClick: (Habit) -> Unit
) : ListAdapter<Habit, HabitAdapter.HabitViewHolder>(HabitDiffCallback()) {

    class HabitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.habitName)
        val description: TextView = view.findViewById(R.id.habitDescription)
        val btnEdit: ImageButton = view.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
        val checkbox: CheckBox = view.findViewById(R.id.checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habit, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = getItem(position)
        holder.name.text = habit.name
        holder.description.text = habit.description

        holder.btnEdit.setOnClickListener { onEditClick(habit) }
        holder.btnDelete.setOnClickListener { onDeleteClick(habit) }
        
        holder.checkbox.setOnCheckedChangeListener(null)
        holder.checkbox.isChecked = false 
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
