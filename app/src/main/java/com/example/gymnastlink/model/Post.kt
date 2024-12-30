package com.example.gymnastlink.model

import java.time.LocalDate

data class Post(
    val userName: String,
    val userTitle: String,
    val title: String,
    val content: String,
    val image: Int?,
    val likeCount: Int,
    val date: LocalDate
)
