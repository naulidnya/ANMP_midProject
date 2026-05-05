package com.nauli.anmp_uts.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nauli.anmp_uts.databinding.FragmentHabitCardBinding
import com.nauli.anmp_uts.databinding.ItemHabitBinding
import com.nauli.anmp_uts.model.Habit
import com.nauli.anmp_uts.viewmodel.HabitListViewModel

class HabitListAdapter(
    private val habitList: ArrayList<Habit>,
    private val viewmodel: HabitListViewModel

) : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {
    class HabitViewHolder(
        val binding: FragmentHabitCardBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HabitViewHolder {

        val binding = FragmentHabitCardBinding.inflate(
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
        holder.binding.imageView.setImageResource(
            habit.icon
        )

        holder.binding.namaTxt.text = habit.title
        holder.binding.deskripsiTxt.text = habit.description

        holder.binding.progressBarHabit.max = habit.target
        holder.binding.progressBarHabit.progress = habit.progress
        holder.binding.progressTxt.text =
            "${habit.progress} / ${habit.target}"

        if (habit.progress >= habit.target) {
            holder.binding.statusTxt.text = "Completed"
            holder.binding.statusTxt.setTextColor(
                Color.parseColor("#4CAF50")
            )

            //disable tombol
            holder.binding.btnAdd.isEnabled = false
            holder.binding.btnReduce.isEnabled = false

            holder.binding.btnAdd.alpha = 0.6f
            holder.binding.btnReduce.alpha = 0.6f

        } else {
            holder.binding.statusTxt.text = "In Progress"
            holder.binding.statusTxt.setTextColor(
                Color.parseColor("#6200EE")
            )

            //enable tombol
            holder.binding.btnAdd.isEnabled = true
            holder.binding.btnReduce.isEnabled = true

            holder.binding.btnAdd.alpha = 1f
            holder.binding.btnReduce.alpha = 1f

        }
        holder.binding.btnAdd.setOnClickListener {
            if (habit.progress < habit.target) {
                habit.progress++
                notifyItemChanged(position)
            }
        }

        holder.binding.btnReduce.setOnClickListener {
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