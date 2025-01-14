package com.example.gymnastlink.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDate

@Entity
data class Post(
    val userName: String,
    val userTitle: String,
    @PrimaryKey val postId: String,
    val title: String,
    val content: String,
    val image: String?,
    val likeCount: Int,
    val date: LocalDate
){
    companion object {

        const val USER_NAME_KEY = "userName"
        const val USER_TITLE_KEY = "userTitle"
        const val POST_ID_KEY = "postId"
        const val TITLE_KEY = "title"
        const val CONTENT_KEY = "content"
        const val IMAGE_KEY = "image"
        const val LIKE_COUNT_KEY = "likeCount"
        const val DATE_KEY = "date"

        fun fromJSON(json: Map<String, Any>): Post {
            val userName = json[USER_NAME_KEY] as? String ?: ""
            val userTitle = json[USER_TITLE_KEY] as? String ?: ""
            val postId = json[POST_ID_KEY] as? String ?: ""
            val title = json[TITLE_KEY] as? String ?: ""
            val content = json[CONTENT_KEY] as? String ?: ""
            val image = json[IMAGE_KEY] as? String ?: ""
            val likeCount = json[LIKE_COUNT_KEY] as? Int ?: 0
            val date = json[DATE_KEY] as? LocalDate ?: LocalDate.now()

            return Post(
                userName = userName,
                userTitle = userTitle,
                postId = postId,
                title = title,
                content = content,
                image = image,
                likeCount = likeCount,
                date = date
            )
        }
    }

    val json: HashMap<String, Serializable?>
        get() = hashMapOf(
            USER_NAME_KEY to userName,
            USER_TITLE_KEY to userTitle,
            POST_ID_KEY to postId,
            TITLE_KEY to title,
            CONTENT_KEY to content,
            IMAGE_KEY to image,
            LIKE_COUNT_KEY to likeCount,
            DATE_KEY to date
        )
}
