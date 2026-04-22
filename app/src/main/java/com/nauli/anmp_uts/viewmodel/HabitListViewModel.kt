package com.nauli.anmp_uts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nauli.anmp_uts.model.Habit
import com.nauli.anmp_uts.R

class HabitListViewModel : ViewModel() {

    val habitList = MutableLiveData<ArrayList<Habit>>()

    fun loadHabits() {

        val list = arrayListOf<Habit>()

        // DRINK WATER
        list.add(
            Habit(
                title = "Drink Water",
                description = "Stay hydrated throughout the day",
                progress = 3,
                target = 8,
                unit = "glasses",
                iconResId = R.drawable.baseline_water_drop_24
            )
        )

        // EXERCISE
        list.add(
            Habit(
                title = "Exercise",
                description = "Daily workout routine",
                progress = 15,
                target = 30,
                unit = "minutes",
                iconResId = R.drawable.baseline_fitness_center_24
            )
        )

        // READ BOOK
        list.add(
            Habit(
                title = "Read Book",
                description = "Expand your knowledge",
                progress = 20,
                target = 20,
                unit = "pages",
                iconResId = R.drawable.baseline_menu_book_24
            )
        )

        // MEDITATION
        list.add(
            Habit(
                title = "Meditation",
                description = "Mindfulness practice",
                progress = 5,
                target = 10,
                unit = "minutes",
                iconResId = R.drawable.baseline_self_improvement_24
            )
        )

        habitList.value = list
    }
}