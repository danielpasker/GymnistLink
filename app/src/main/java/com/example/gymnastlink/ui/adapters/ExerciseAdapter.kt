package com.example.gymnastlink.ui.adapters

import ExerciseItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gymnastlink.R
import com.bumptech.glide.Glide
import com.example.gymnastlink.ui.fragments.WorkoutsFragmentDirections

class ExerciseAdapter(private val exercises: List<ExerciseItem>) :
    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {


    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseName: TextView = itemView.findViewById(R.id.exercise_name)
        val exerciseImage: ImageView = itemView.findViewById(R.id.exercise_image)
        val exerciseMainMuscle: TextView = itemView.findViewById(R.id.exercise_main_muscle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.exerciseName.text = exercise.name
        holder.exerciseMainMuscle.text = exercise.target.name

        Glide.with(holder.itemView.context)
            .load(exercise.gifUrl)
            .placeholder(R.drawable.placeholder_image)
            .into(holder.exerciseImage)

        val action = WorkoutsFragmentDirections.actionWorkoutsFragmentToExerciseDetailsFragment(position)
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = exercises.size
}