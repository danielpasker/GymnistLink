package com.example.gymnastlink.model.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gymnastlink.model.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM Post")
    fun getAllPosts(): List<Post>

    @Query("SELECT * FROM Post WHERE postId =:id")
    fun getPostById(id: String): Post

    @Query("SELECT * FROM Post WHERE userName =:useName")
    fun getPostByUserName(useName: String): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg post: Post)

    @Delete
    fun delete(post: Post)

}