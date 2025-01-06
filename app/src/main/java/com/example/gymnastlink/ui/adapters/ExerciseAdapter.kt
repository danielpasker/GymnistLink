package com.example.gymnastlink.ui.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymnastlink.R
import com.example.gymnastlink.model.ExerciseItem

class ExerciseAdapter(private val exercises: List<ExerciseItem>) :
    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.exercise_name)
        val mainMuscle: TextView = itemView.findViewById(R.id.exercise_main_muscle)
        val exerciseImage: ImageView = itemView.findViewById(R.id.exercise_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]

        holder.name.text = exercise.name
        holder.mainMuscle.text = exercise.mainMuscle.toString()
        exercise.image?.let {
            BitmapFactory.decodeByteArray(it, 0, it.size)?.let { bitmap ->
                holder.exerciseImage.setImageBitmap(bitmap)
            }
        }
    }

    override fun getItemCount(): Int = exercises.size
}