package com.nauli.anmp_uts.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nauli.anmp_uts.databinding.ItemHabitBinding
import com.nauli.anmp_uts.model.Habit

class HabitListAdapter(
    private val habitList: ArrayList<Habit>
) : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    class HabitViewHolder(
        val binding: ItemHabitBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HabitViewHolder {

        val binding = ItemHabitBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HabitViewHolder,
        position: Int
    ) {

        val habit = habitList[position]

        // ICON HABIT
        holder.binding.imgHabit.setImageResource(
            habit.iconResId
        )

        // TITLE
        holder.binding.txtTitle.text = habit.title

        // DESCRIPTION
        holder.binding.txtDescription.text = habit.description

        // PROGRESS BAR
        holder.binding.progressBar.max = habit.target
        holder.binding.progressBar.progress = habit.progress

        // PROGRESS NUMBER
        holder.binding.txtProgressNumber.text =
            "${habit.progress} / ${habit.target}"

        // STATUS + ICON CENTANG (SESUI PPT)
        if (habit.progress >= habit.target) {

            // COMPLETED
            holder.binding.txtStatus.text = "Completed"
            holder.binding.txtStatus.setTextColor(
                Color.parseColor("#4CAF50")
            )

            holder.binding.imgStatus.visibility = View.VISIBLE
            holder.binding.imgStatus.setColorFilter(
                Color.parseColor("#4CAF50")
            )

        } else {

            // IN PROGRESS
            holder.binding.txtStatus.text = "In Progress"
            holder.binding.txtStatus.setTextColor(
                Color.parseColor("#6200EE")
            )

            holder.binding.imgStatus.visibility = View.GONE
        }

        // BUTTON PLUS
        holder.binding.btnPlus.setOnClickListener {
            if (habit.progress < habit.target) {
                habit.progress++
                notifyItemChanged(position)
            }
        }

        // BUTTON MINUS
        holder.binding.btnMinus.setOnClickListener {
            if (habit.progress > 0) {
                habit.progress--
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return habitList.size
    }

    fun updateHabitList(newHabitList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }
}