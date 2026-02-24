package com.example.habit_trawcker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Paint

class HabitAdapter( private val habits: MutableList<Habit>,
    private val onCheckedChange: (Habit, Boolean) -> Unit
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    class HabitViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.habitName)
        val description: TextView = view.findViewById(R.id.habitDescription)

        val checkCompleted: CheckBox = itemView.findViewById(R.id.checkCompleted)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habit, parent, false)
        return HabitViewHolder(view)
    }

    /*override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habits[position]

        holder.name.text = habit.name
        holder.description.text = habit.description
        holder.checkCompleted.isChecked = habit.isCompleted

        holder.checkCompleted.setOnCheckedChangeListener { _, isChecked ->
            onCheckedChange(habit, isChecked)
        }

    } */
    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habits[position]

        holder.name.text = habit.name
        holder.description.text = habit.description

        holder.checkCompleted.setOnCheckedChangeListener(null)
        holder.checkCompleted.isChecked = habit.isCompleted

        holder.checkCompleted.setOnCheckedChangeListener { _, isChecked ->
            onCheckedChange(habit, isChecked)
        }
        if (habit.isCompleted) {
            holder.name.paintFlags =
                holder.name.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.name.paintFlags =
                holder.name.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun getItemCount() = habits.size

    fun updateList(newList: List<Habit>) {
        habits.clear()
        habits.addAll(newList)
        notifyDataSetChanged()
    }

}
