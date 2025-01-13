    package com.example.gymnastlink.model

    import android.os.Looper
    import androidx.core.os.HandlerCompat
    import com.example.gymnastlink.model.dao.LocalDataBase
    import com.example.gymnastlink.model.dao.LocalDataBaseRepository
    import java.util.concurrent.Executors

    class PostModel private constructor(){

        private val database: LocalDataBaseRepository = LocalDataBase.database
        private val executer = Executors.newSingleThreadExecutor()
        private var mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())

        companion object {
            val shared = PostModel()
        }

        fun getAllPosts(callback: (List<Post>) -> Unit) {
            executer.execute {
                val posts = database.postDao().getAllPosts()

                mainHandler.post {
                    callback(posts)
                }
            }
        }

        fun addPost(post: Post, callback: () -> Unit) {
            executer.execute {
                database.postDao().insertAll(post, post, post)

                mainHandler.post {
                    callback()
                }
            }
        }
    }