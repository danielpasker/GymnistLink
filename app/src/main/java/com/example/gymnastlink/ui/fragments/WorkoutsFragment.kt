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
    private lateinit var exerciseRecyclerView: RecyclerView
    private lateinit var adapter: ExerciseAdapter

    companion object {
        val exerciseList = mutableListOf<Any>()
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
        changeSearchDisplay()

        exerciseRecyclerView = view.findViewById(R.id.exercises_recyclerview)
        exerciseRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ExerciseAdapter(exerciseList)
        exerciseRecyclerView.adapter = adapter
        // TODO: needs to be removed, get data from real source
        exerciseList.clear()
        loadSearchedExercises()
        loadMyExercises()
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
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
    // TODO: needs to be changed, get data from real source
    private fun loadSearchedExercises(){
        exerciseList.add("Recently Searched")
        val recentlySearchedIndex = exerciseList.indexOf("Recently Searched")
        if (recentlySearchedIndex != -1) {
            val exercises = listOf(
                ExerciseItem("John Smith", BodyMuscle.BICEPS, null),
                ExerciseItem("Yoni Yonitan", BodyMuscle.DELTOID, null),
                ExerciseItem("Yael Cohen", BodyMuscle.FOREARMS, null)
            )
            exerciseList.addAll(recentlySearchedIndex + 1, exercises)
            adapter.notifyItemRangeInserted(recentlySearchedIndex + 1, exercises.size)
        }
    }
    // TODO: needs to be changed, get data from real source
    private fun loadMyExercises(){
        exerciseList.add("My Plan")
        val myPlanIndex = exerciseList.indexOf("My Plan")
        if (myPlanIndex != -1) {
            val exercises1 = listOf(
                ExerciseItem("Rotem Sela", BodyMuscle.GLUTEUS_MAXIMUS,null),
                ExerciseItem("Miri Meirman", BodyMuscle.QUADRICEPS, null),
                ExerciseItem("Yael Atias", BodyMuscle.FOREARMS, null)
            )
            exerciseList.addAll(myPlanIndex + 1, exercises1)
            adapter.notifyItemRangeInserted(myPlanIndex + 1, exercises1.size)
        }
    }
}