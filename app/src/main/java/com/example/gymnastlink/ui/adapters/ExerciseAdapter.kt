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
import com.example.gymnastlink.ui.fragments.onExerciseItemClickListener

class ExerciseAdapter(private val exercises: List<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    lateinit var listener: onExerciseItemClickListener

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.findViewById(R.id.titleText)

        fun bind(title: String) {
            titleText.text = title
        }
    }

    class ExerciseViewHolder(itemView: View, listener: onExerciseItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.exercise_name)
        val mainMuscle: TextView = itemView.findViewById(R.id.exercise_main_muscle)
        val exerciseImage: ImageView = itemView.findViewById(R.id.exercise_image)
        lateinit var exerciseItem: ExerciseItem


        init {
            itemView.setOnClickListener{
                listener?.onExerciseClick(adapterPosition, exerciseItem)
            }
        }

        fun bind(exercise: ExerciseItem){
            exerciseItem = exercise
            name.text = exercise.name
            mainMuscle.text = exercise.mainMuscle.toString()
            exercise.image?.let {
                BitmapFactory.decodeByteArray(it, 0, it.size)?.let { bitmap ->
                    exerciseImage.setImageBitmap(bitmap)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (exercises[position] is String) TYPE_HEADER else TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.exercises_header, parent, false))
            TYPE_ITEM -> ExerciseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false), listener)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(exercises[position] as String)
            is ExerciseViewHolder -> holder.bind(exercises[position] as ExerciseItem)
        }
    }

    override fun getItemCount(): Int = exercises.size
}