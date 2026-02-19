package com.example.habit_trawcker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitAdapter(
    private val habits: MutableList<Habit>,
    private val onEditClick: (Habit) -> Unit,
    private val onDeleteClick: (Habit) -> Unit
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

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
        val habit = habits[position]
        holder.name.text = habit.name
        holder.description.text = habit.description

        holder.btnEdit.setOnClickListener { onEditClick(habit) }
        holder.btnDelete.setOnClickListener { onDeleteClick(habit) }
        
        // Här kan du även hantera checkboxen om du vill spara status
        holder.checkbox.setOnCheckedChangeListener(null)
        holder.checkbox.isChecked = false // Standardvärde
    }

    override fun getItemCount() = habits.size

    fun updateList(newList: List<Habit>) {
        habits.clear()
        habits.addAll(newList)
        notifyDataSetChanged()
    }
}
