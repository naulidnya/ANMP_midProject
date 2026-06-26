package com.nauli.anmp_uts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.nauli.anmp_uts.viewmodel.HabitListViewModel

class Fragment_MakeHabit : Fragment() {

    private lateinit var viewModel: HabitListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment__make_habit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())
            .get(HabitListViewModel::class.java)

        val btnSave = view.findViewById<Button>(R.id.btnSave)

        val habitName = view.findViewById<TextInputLayout>(R.id.habitNameTxt).editText
        val habitDesc = view.findViewById<TextInputLayout>(R.id.habitDescTxt).editText
        val targetHabit = view.findViewById<TextInputLayout>(R.id.habitGoalTxt).editText
        val unitHabit = view.findViewById<TextInputLayout>(R.id.habitUnitTxt).editText
        val spinnerHabit = view.findViewById<Spinner>(R.id.habitComboBox)

        // Spinner Icon
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.icon_list,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerHabit.adapter = adapter

        btnSave.setOnClickListener {

            val title = habitName?.text.toString()
            val desc = habitDesc?.text.toString()
            val unit = unitHabit?.text.toString()
            val pilihIcon = spinnerHabit.selectedItem.toString()

            val target = try {
                targetHabit?.text.toString().toInt()
            } catch (e: Exception) {
                0
            }

            // INSERT KE ROOM
            viewModel.tambahHabit(
                title = title,
                desc = desc,
                target = target,
                unit = unit,
                icon = pilihIcon
            )

            // KEMBALI KE DASHBOARD
            findNavController().popBackStack()
        }
    }
}