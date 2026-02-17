package com.example.habit_trawcker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitAdapter(
    private val habits: MutableList<Habit>
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    class HabitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.habitName)
        val description: TextView = view.findViewById(R.id.habitDescription)
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
    }

    override fun getItemCount() = habits.size

    fun updateList(newList: List<Habit>) {
        habits.clear()
        habits.addAll(newList)
        notifyDataSetChanged()
    }

}
