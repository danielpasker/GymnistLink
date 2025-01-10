package com.example.gymnastlink.ui.fragments

import ExerciseItem
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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymnastlink.R
import com.example.gymnastlink.model.BodyPart
import com.example.gymnastlink.model.Equipment
import com.example.gymnastlink.model.TargetMuscle
import com.example.gymnastlink.ui.MainActivity
import com.example.gymnastlink.ui.adapters.ExerciseAdapter
import java.lang.reflect.Field

interface onExerciseItemClickListener {
    fun onExerciseClick(position: Int, exerciseItem: ExerciseItem)
}

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
        (activity as? MainActivity)?.setFragmentTitle(getString(R.string.workouts))

        searchView = view.findViewById(R.id.workout_search)
        changeSearchViewHintTextDisplay()

        exerciseRecyclerView = view.findViewById(R.id.exercises_recyclerview)
        exerciseRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ExerciseAdapter(exerciseList)

        adapter.listener = object : onExerciseItemClickListener {
            override fun onExerciseClick(position: Int, exerciseItem: ExerciseItem) {
                val bundle = Bundle().apply {
                    putInt("position", position)
                    putParcelable("exerciseItem", exerciseItem)
                }
                findNavController().navigate(R.id.action_workoutsFragment_to_exerciseDetailsFragment, bundle)
            }
        }

        exerciseRecyclerView.adapter = adapter
        // TODO: needs to be removed, get data from real source
        if (exerciseList.isEmpty()) {
            loadSearchedExercises()
            loadMyExercises()
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun changeSearchViewHintTextDisplay() {
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
        exerciseList.add(getString(R.string.recently_searched_header))
        val recentlySearchedIndex = exerciseList.indexOf(getString(R.string.recently_searched_header))
        if (recentlySearchedIndex != -1) {
            val exercises = listOf(
                ExerciseItem("1","Dumbbells",Equipment.DUMBBELL,TargetMuscle.BICEPS,
                    BodyPart.UPPER_ARMS,emptyArray(), arrayOf(
                        "sit in a chair",
                        "hold the dumbbells",
                        "work on your biceps with the dumbbells"
                    ),""),
                ExerciseItem("2","Leg Extinction",Equipment.ELLIPTICAL_MACHINE,TargetMuscle.QUADS,
                    BodyPart.UPPER_ARMS,emptyArray(), arrayOf(
                        "get your legs up",
                        "get your legs down"
                    ),""),
                ExerciseItem("3","Pull Ups",Equipment.BODY_WEIGHT,TargetMuscle.SPINE,
                    BodyPart.UPPER_ARMS,emptyArray(), arrayOf(
                        "Get up and down with all of your body"
                    ),"")
            )
            exerciseList.addAll(recentlySearchedIndex + 1, exercises)
            adapter.notifyItemRangeInserted(recentlySearchedIndex + 1, exercises.size)
        }
    }
    // TODO: needs to be changed, get data from real source
    private fun loadMyExercises(){
        exerciseList.add(getString(R.string.my_plan_header))
        val myPlanIndex = exerciseList.indexOf(getString(R.string.my_plan_header))
        if (myPlanIndex != -1) {
            val exercises1 = listOf(
                ExerciseItem("4","Dumbbells",Equipment.UNKNOWN,TargetMuscle.BICEPS,
                    BodyPart.UPPER_ARMS,emptyArray(),emptyArray(),""),
                ExerciseItem("5","Dumbbells",Equipment.UNKNOWN,TargetMuscle.BICEPS,
                    BodyPart.UPPER_ARMS,emptyArray(),emptyArray(),""),
                ExerciseItem("6","Dumbbells",Equipment.UNKNOWN,TargetMuscle.BICEPS,
                    BodyPart.UPPER_ARMS,emptyArray(),emptyArray(),"")
            )
            exerciseList.addAll(myPlanIndex + 1, exercises1)
            adapter.notifyItemRangeInserted(myPlanIndex + 1, exercises1.size)
        }
    }
}