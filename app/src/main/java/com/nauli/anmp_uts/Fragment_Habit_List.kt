package com.nauli.anmp_uts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nauli.anmp_uts.view.HabitListAdapter
import com.nauli.anmp_uts.viewmodel.HabitListViewModel

class Fragment_Habit_List : Fragment() {

    private lateinit var viewModel: HabitListViewModel
    private lateinit var habitListAdapter: HabitListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.fragment__habit__list,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val recViewHabit =
            view.findViewById<RecyclerView>(R.id.recViewHabit)

        val addButton =
            view.findViewById<FloatingActionButton>(R.id.addButton)

        viewModel = ViewModelProvider(requireActivity())
            .get(HabitListViewModel::class.java)

        viewModel.loadDataPreferences(requireContext())

        habitListAdapter =
            HabitListAdapter(arrayListOf(), viewModel)

        recViewHabit.layoutManager =
            LinearLayoutManager(context)

        recViewHabit.adapter =
            habitListAdapter

        addButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_fragment_Habit_List_to_fragment_MakeHabit
            )
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.habitList.observe(viewLifecycleOwner) {
            habitListAdapter.updateHabitList(it)

        }
    }
}