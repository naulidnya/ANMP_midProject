package com.nauli.anmp_uts.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.nauli.anmp_uts.R
import com.nauli.anmp_uts.databinding.FragmentHabitCardBinding
import com.nauli.anmp_uts.model.Habit
import com.nauli.anmp_uts.viewmodel.HabitListViewModel

class HabitListAdapter(
    private val viewModel: HabitListViewModel
) : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    private var habitList = listOf<Habit>()

    class HabitViewHolder(
        val binding: FragmentHabitCardBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HabitViewHolder {

        val binding = FragmentHabitCardBinding.inflate(
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

        holder.binding.habit = habit

        holder.binding.namaTxt.setOnClickListener {

            Navigation.findNavController(it)
                .navigate(R.id.action_editHabit)

        }

        holder.binding.btnAdd.setOnClickListener {

            viewModel.tambahProses(habit)

        }

        holder.binding.btnReduce.setOnClickListener {

            viewModel.kurangProses(habit)

        }

        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return habitList.size
    }

    fun setHabitList(list: List<Habit>) {

        habitList = list

        notifyDataSetChanged()
    }
}