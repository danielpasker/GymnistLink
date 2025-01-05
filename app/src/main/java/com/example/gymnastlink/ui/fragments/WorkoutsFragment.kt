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
import android.widget.ImageView
import android.widget.SearchView
import com.example.gymnastlink.R
import com.example.gymnastlink.ui.MainActivity
import java.lang.reflect.Field

class WorkoutsFragment : Fragment() {

    private lateinit var searchView: android.widget.SearchView
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         */
//    }

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