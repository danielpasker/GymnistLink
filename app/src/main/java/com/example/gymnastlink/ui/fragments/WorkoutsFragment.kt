package com.example.gymnastlink.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymnastlink.R
import com.example.gymnastlink.model.BodyMuscle
import com.example.gymnastlink.model.ExerciseItem
import com.example.gymnastlink.ui.MainActivity
import com.example.gymnastlink.ui.adapters.ExerciseAdapter

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

        // TODO: needs to be changed, get data from db
        val dummyPlan = listOf(
            ExerciseItem("Dumbbells", BodyMuscle.BICEPS, null),
            ExerciseItem("Arnold Press", BodyMuscle.DELTOID, null),
            ExerciseItem("Wrist Curl", BodyMuscle.FOREARMS, null)
        )
        myPlan.addAll(dummyPlan)

        myPlanAdapter = ExerciseAdapter(myPlan)
        myPlanRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        myPlanRecyclerView.adapter = myPlanAdapter

        searchResultAdapter = ExerciseAdapter(searchResults)
        searchResultsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        searchResultsRecyclerView.adapter = searchResultAdapter

        workoutSearchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    searchResultsLayout.visibility = View.GONE
                } else {
                    searchResultsLayout.visibility = View.VISIBLE
                    loadSearchResults()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onResume() {
        super.onResume()
        myPlanAdapter.notifyDataSetChanged()
        searchResultAdapter.notifyDataSetChanged()
    }

    // TODO: needs to be changed, get data from real source
    private fun loadSearchResults() {
        val dummyResults = listOf(
            ExerciseItem("Dumbbells", BodyMuscle.BICEPS, null),
            ExerciseItem("Arnold Press", BodyMuscle.DELTOID, null),
            ExerciseItem("Wrist Curl", BodyMuscle.FOREARMS, null)
        )
        searchResults.addAll(dummyResults)
        searchResultAdapter.notifyDataSetChanged()
    }
}