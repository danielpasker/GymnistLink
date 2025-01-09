package com.example.gymnastlink.model

data class ExerciseItem(
    val name: String,
    val mainMuscle: BodyMuscle,
    val image: ByteArray?
)
