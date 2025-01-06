package com.example.gymnastlink.ui.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymnastlink.R
import com.example.gymnastlink.model.BodyMuscle
import com.example.gymnastlink.model.ExerciseItem
import com.example.gymnastlink.ui.MainActivity
import com.example.gymnastlink.ui.adapters.ExerciseAdapter
import java.lang.reflect.Field

class WorkoutsFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var searchResultRecyclerView: RecyclerView
    private lateinit var myPlanRecyclerView: RecyclerView
    private lateinit var searchResultAdapter: ExerciseAdapter
    private lateinit var myPlanAdapter: ExerciseAdapter

    companion object {
        val searchResultList = mutableListOf<ExerciseItem>()
        val myPlanList = mutableListOf<ExerciseItem>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workouts, container, false)
    }

    @SuppressLint("DiscouragedPrivateApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.updateFragmentTitle(getString(R.string.workouts))

        searchView = view.findViewById(R.id.workout_search)
        searchResultRecyclerView = view.findViewById(R.id.search_results_recyclerview)
        searchResultRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        myPlanRecyclerView = view.findViewById(R.id.my_plan_recyclerview)
        myPlanRecyclerView.layoutManager = LinearLayoutManager(requireContext())

//        TODO: needs to be removed, get data from real source
        if(searchResultList.isEmpty()){
            searchResultList.add(
                ExerciseItem(
                    "John Smith",
                    BodyMuscle.BICEPS,
                    null
                )
            )
            searchResultList.add(
                ExerciseItem(
                    "Yoni Yonitan",
                    BodyMuscle.DELTOID,
                    null
                )
            )
            searchResultList.add(
                ExerciseItem(
                    "Yael Cohen",
                    BodyMuscle.FOREARMS,
                    null
                )
            )
        }

        if(myPlanList.isEmpty()){
            myPlanList.add(
                ExerciseItem(
                    "Rotem Sela",
                    BodyMuscle.GLUTEUS_MAXIMUS,
                    null
                )
            )
            myPlanList.add(
                ExerciseItem(
                    "Miri Meirman",
                    BodyMuscle.QUADRICEPS,
                    null
                )
            )
            myPlanList.add(
                ExerciseItem(
                    "Yael Atias",
                    BodyMuscle.FOREARMS,
                    null
                )
            )
        }

        searchResultAdapter = ExerciseAdapter(searchResultList)
        searchResultRecyclerView.adapter = searchResultAdapter
        myPlanAdapter = ExerciseAdapter(myPlanList)
        myPlanRecyclerView.adapter = myPlanAdapter

        changeSearchDisplay()
    }

    override fun onResume() {
        super.onResume()
        searchResultAdapter.notifyDataSetChanged()
        myPlanAdapter.notifyDataSetChanged()
    }

    private fun changeSearchDisplay() {
        try {
            val searchEditTextField: Field = SearchView::class.java.getDeclaredField("mSearchSrcTextView")
            searchEditTextField.isAccessible = true
            val searchEditText: EditText = searchEditTextField.get(searchView) as EditText
            searchEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10f)
            searchEditText.setHintTextColor(Color.WHITE)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
}