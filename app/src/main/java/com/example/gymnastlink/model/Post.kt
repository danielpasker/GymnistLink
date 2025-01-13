package com.example.gymnastlink.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Post(
    val userName: String,
    val userTitle: String,
    @PrimaryKey val postId: String,
    val title: String,
    val content: String,
    val image: ByteArray?,
    val likeCount: Int,
    val date: LocalDate
)
