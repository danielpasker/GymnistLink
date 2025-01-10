package com.example.gymnastlink.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymnastlink.R
import com.example.gymnastlink.model.BodyMuscle
import com.example.gymnastlink.model.ExerciseItem
import com.example.gymnastlink.ui.MainActivity
import com.example.gymnastlink.ui.adapters.ExerciseAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorkoutsFragment : Fragment() {

    private lateinit var workoutSearchEditText: EditText
    private lateinit var searchResultsLayout: View
    private lateinit var searchResultsRecyclerView: RecyclerView
    private lateinit var myPlanRecyclerView: RecyclerView
    private lateinit var searchResultAdapter: ExerciseAdapter
    private lateinit var myPlanAdapter: ExerciseAdapter

    companion object {
        val searchResults = mutableListOf<ExerciseItem>()
        val myPlan = mutableListOf<ExerciseItem>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workouts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setFragmentTitle(getString(R.string.workouts))

        workoutSearchEditText = view.findViewById(R.id.workout_search)
        searchResultsLayout = view.findViewById(R.id.search_results_layout)
        searchResultsRecyclerView = view.findViewById(R.id.search_results_recyclerview)
        myPlanRecyclerView = view.findViewById(R.id.my_plan_recyclerview)

        myPlanAdapter = ExerciseAdapter(myPlan)
        myPlanRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        myPlanRecyclerView.adapter = myPlanAdapter
        myPlanRecyclerView.isNestedScrollingEnabled = false

        searchResultAdapter = ExerciseAdapter(searchResults)
        searchResultsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        searchResultsRecyclerView.adapter = searchResultAdapter
        searchResultsRecyclerView.isNestedScrollingEnabled = false

        workoutSearchEditText.addTextChangedListener(createTextWatcher())

        // Load data asynchronously
        lifecycleScope.launch {
            loadMyExercises()
        }
    }

    override fun onResume() {
        super.onResume()
        myPlanAdapter.notifyDataSetChanged()
        searchResultAdapter.notifyDataSetChanged()
    }

    private fun createTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    searchResultsLayout.visibility = View.GONE
                } else {
                    searchResultsLayout.visibility = View.VISIBLE
                    // Filter search results asynchronously
                    lifecycleScope.launch {
                        getSearchResults(s.toString())
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }
    }

    private suspend fun loadMyExercises() {
        withContext(Dispatchers.IO) {
            // Simulate data loading
            val dummyPlan = listOf(
                ExerciseItem("Dumbbells", BodyMuscle.BICEPS, null),
                ExerciseItem("Arnold Press", BodyMuscle.DELTOID, null),
                ExerciseItem("Wrist Curl", BodyMuscle.FOREARMS, null)
            )
            myPlan.clear()
            myPlan.addAll(dummyPlan)
        }
        withContext(Dispatchers.Main) {
            myPlanAdapter.notifyDataSetChanged()
        }
    }

    private suspend fun getSearchResults(query: String) {
        val dummyResults = listOf(
            ExerciseItem("Dumbbells", BodyMuscle.BICEPS, null),
            ExerciseItem("Arnold Press", BodyMuscle.DELTOID, null),
            ExerciseItem("Wrist Curl", BodyMuscle.FOREARMS, null)
        )

        withContext(Dispatchers.IO) {
            // Simulate filtering logic
            val filteredResults = dummyResults.filter {
                it.name.contains(query, ignoreCase = true)
            }
            searchResults.clear()
            searchResults.addAll(filteredResults)
        }
        withContext(Dispatchers.Main) {
            searchResultAdapter.notifyDataSetChanged()
        }
    }
}