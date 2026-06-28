package com.nauli.anmp_uts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.nauli.anmp_uts.Fragment_EditHabitsArgs
import com.nauli.anmp_uts.model.Habit
import com.nauli.anmp_uts.viewmodel.HabitListViewModel

class Fragment_EditHabits : Fragment() {
    private var habitId = 0
    private lateinit var viewModel: HabitListViewModel
    private lateinit var currentHabit: Habit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args =
                Fragment_EditHabitsArgs.fromBundle(it)

            habitId = args.habitId
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment__edit_habits, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())
            .get(HabitListViewModel::class.java)

        val habitName =
            view.findViewById<TextInputLayout>(R.id.habitNameTxt).editText

        val habitDesc =
            view.findViewById<TextInputLayout>(R.id.habitDescTxt).editText

        val targetHabit =
            view.findViewById<TextInputLayout>(R.id.habitGoalTxt).editText

        val unitHabit =
            view.findViewById<TextInputLayout>(R.id.habitUnitTxt).editText

        val spinnerHabit =
            view.findViewById<Spinner>(R.id.habitComboBox)

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.icon_list,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinnerHabit.adapter = adapter
        viewModel.getHabit(habitId) { habit ->
            currentHabit = habit
            habitName?.setText(habit.title)
            habitDesc?.setText(habit.description)
            targetHabit?.setText(habit.target.toString())
            unitHabit?.setText(habit.unit)

            val position = when (habit.icon) {
                R.drawable.baseline_water_drop_24 -> 0
                R.drawable.baseline_fitness_center_24 -> 1
                R.drawable.outline_bed_24 -> 2
                R.drawable.minutes -> 3
                R.drawable.pages -> 4
                else -> 5
            }
            spinnerHabit.setSelection(position)
        }

        val btnSave =
            view.findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener {

            currentHabit.title =
                habitName?.text.toString()

            currentHabit.description =
                habitDesc?.text.toString()

            currentHabit.target =
                targetHabit?.text.toString().toInt()

            currentHabit.unit =
                unitHabit?.text.toString()

            currentHabit.icon =
                when (spinnerHabit.selectedItem.toString()) {

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

            viewModel.editHabit(currentHabit)
            findNavController().popBackStack()
        }
    }

}