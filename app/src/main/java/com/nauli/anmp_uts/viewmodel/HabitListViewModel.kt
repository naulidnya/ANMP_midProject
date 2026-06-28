package com.nauli.anmp_uts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nauli.anmp_uts.R
import com.nauli.anmp_uts.model.Habit
import com.nauli.anmp_uts.model.HabitDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class HabitListViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val habitDao =
        HabitDatabase
            .getInstance(application)
            .habitDao()

    val habitList: LiveData<List<Habit>> =
        habitDao.getAllHabit()

    fun tambahProses(habit: Habit) {

        if (habit.progress < habit.target) {

            habit.progress++

            updateHabit(habit)
        }
    }

    fun kurangProses(habit: Habit) {

        if (habit.progress > 0) {

            habit.progress--

            updateHabit(habit)
        }
    }

    fun getHabit(
        id: Int,
        callback: (Habit) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val habit = habitDao.getHabit(id)
            launch(Dispatchers.Main) {
                callback(habit)
            }
        }
    }

    fun editHabit(
        habit: Habit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            habitDao.updateHabit(habit)
        }
    }

    fun tambahHabit(
        title: String,
        desc: String,
        target: Int,
        unit: String,
        icon: String
    ) {

        val iconRes = when (icon) {
            "Glass" ->
                R.drawable.baseline_water_drop_24
            "Fitness" ->
                R.drawable.baseline_fitness_center_24
            "Sleep" ->
                R.drawable.outline_bed_24
            "Minutes" ->
                R.drawable.minutes
            "Pages" ->
                R.drawable.pages
            else ->
                R.drawable.baseline_self_improvement_24
        }

        val newHabit = Habit(
            title = title,
            description = desc,
            progress = 0,
            target = target,
            unit = unit,
            icon = iconRes
        )

        viewModelScope.launch {

            habitDao.insertHabit(newHabit)

        }
    }

    private fun updateHabit(
        habit: Habit
    ) {

        viewModelScope.launch {
            habitDao.updateHabit(habit)

        }
    }
}