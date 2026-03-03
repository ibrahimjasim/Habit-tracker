package com.example.habit_trawcker

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class HabitAdapter(
    private val onEditClick: (Habit) -> Unit,
    private val onDeleteClick: (Habit) -> Unit,
    private val onCheckedChange: (Habit, Boolean) -> Unit
) : ListAdapter<Habit, HabitAdapter.HabitViewHolder>(HabitDiffCallback()) {

    class HabitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: MaterialCardView = view as MaterialCardView
        val iconIndicator: View = view.findViewById(R.id.iconIndicator)
        val name: TextView = view.findViewById(R.id.habitName)
        val description: TextView = view.findViewById(R.id.habitDescription)
        val btnEdit: ImageButton = view.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
        val checkCompleted: CheckBox = view.findViewById(R.id.checkCompleted)
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

        // Edit / Delete
        holder.btnEdit.setOnClickListener { onEditClick(habit) }
        holder.btnDelete.setOnClickListener { onDeleteClick(habit) }

        // Checkbox
        holder.checkCompleted.setOnCheckedChangeListener(null)
        holder.checkCompleted.isChecked = habit.isCompleted

        holder.checkCompleted.setOnCheckedChangeListener { _, isChecked ->
            onCheckedChange(habit, isChecked)
        }

        // Bad habit card tinting
        val ctx = holder.itemView.context
        if (habit.isBad) {
            holder.card.setCardBackgroundColor(
                ContextCompat.getColor(ctx, R.color.bad_habit_red)
            )
            holder.iconIndicator.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(ctx, R.color.bad_habit_icon)
            )
        } else {
            holder.card.setCardBackgroundColor(
                ContextCompat.getColor(ctx, R.color.white)
            )
            holder.iconIndicator.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(ctx, R.color.brand_green)
            )
        }

        // Strike-through effect
        if (habit.isCompleted) {
            holder.name.paintFlags =
                holder.name.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.name.paintFlags =
                holder.name.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
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