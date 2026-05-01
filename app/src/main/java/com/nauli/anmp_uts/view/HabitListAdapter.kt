package com.nauli.anmp_uts.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nauli.anmp_uts.databinding.ItemHabitBinding
import com.nauli.anmp_uts.model.Habit
import com.nauli.anmp_uts.viewmodel.HabitListViewModel

class HabitListAdapter(
    private val habitList: ArrayList<Habit>,
    private val viewmodel: HabitListViewModel

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
            parent, false
        )
        return HabitViewHolder(binding)
    }
    override fun onBindViewHolder(
        holder: HabitViewHolder,
        position: Int
    ) {

        val habit = habitList[position]
        holder.binding.imgHabit.setImageResource(
            habit.icon
        )

        holder.binding.txtTitle.text = habit.title
        holder.binding.txtDescription.text = habit.description

        holder.binding.progressBar.max = habit.target
        holder.binding.progressBar.progress = habit.progress
        holder.binding.txtProgressNumber.text =
            "${habit.progress} / ${habit.target}"

        if (habit.progress >= habit.target) {
            holder.binding.txtStatus.text = "Completed"
            holder.binding.txtStatus.setTextColor(
                Color.parseColor("#4CAF50")
            )

            holder.binding.imgStatus.visibility = View.VISIBLE
            holder.binding.imgStatus.setColorFilter(
                Color.parseColor("#4CAF50")
            )

        } else {
            holder.binding.txtStatus.text = "In Progress"
            holder.binding.txtStatus.setTextColor(
                Color.parseColor("#6200EE")
            )

            holder.binding.imgStatus.visibility = View.GONE
        }
        holder.binding.btnPlus.setOnClickListener {
            if (habit.progress < habit.target) {
                habit.progress++
                notifyItemChanged(position)
            }
        }

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