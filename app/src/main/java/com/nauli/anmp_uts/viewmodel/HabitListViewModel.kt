package com.nauli.anmp_uts.viewmodel

import android.content.Context
import android.graphics.drawable.Icon
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.nauli.anmp_uts.model.Habit
import com.nauli.anmp_uts.R

class HabitListViewModel : ViewModel() {

    fun simpanDataPreferences(context: Context) {
        val sharedPref = context.getSharedPreferences("habit_preferences", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val gson = Gson()
        val json = gson.toJson(habitList.value)

        editor.putString("habit_list", json)
        editor.apply()
    }

    fun loadDataPreferences(context: Context) {
        val sharedPref = context.getSharedPreferences("habit_preferences", Context.MODE_PRIVATE)
        val json = sharedPref.getString("habit_list", null)
        if (json != null) {
            val gson = Gson()
            val type = object : com.google.gson.reflect.TypeToken<ArrayList<Habit>>() {}.type
            val list: ArrayList<Habit> = gson.fromJson(json, type)

            habitList.value = list
        }
    }

    val habitList = MutableLiveData<ArrayList<Habit>>()
    fun tambahProses(position: Int) {
        habitList.value?.let {
            if (it[position].progress < it[position].target) {
                it[position].progress++
                habitList.value = ArrayList(it)
            }
        }
    }
    fun kurangProses(position: Int) {
        habitList.value?.let {
            if (it[position].progress > 0) {
                it[position].progress--
                habitList.value = ArrayList(it)
            }
        }
    }

    fun TambahHabit(title: String, desc: String, target: Int, unit: String, icon: String ) {

        val currentList = habitList.value ?: arrayListOf()

        var iconRes = R.drawable.baseline_self_improvement_24

        if (icon == "Glass") {
            iconRes = R.drawable.baseline_water_drop_24
        } else if (icon == "Fitness") {
            iconRes = R.drawable.baseline_fitness_center_24
        } else if (icon == "Sleep") {
            iconRes = R.drawable.outline_bed_24
        } else if (icon == "Minutes") {
            iconRes = R.drawable.minutes
        } else if (icon == "Pages") {
            iconRes = R.drawable.pages
        }

        val newHabit = Habit(
            title = title,
            description = desc,
            progress = 0,
            target = target,
            unit = unit,
            icon = iconRes
        )

        currentList.add(newHabit)
        habitList.value = ArrayList(currentList)
    }
}