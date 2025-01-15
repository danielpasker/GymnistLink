package com.example.gymnastlink.model

class PostModel private constructor(){

    private val firebaseModel = FirebaseModel()

    companion object {
        val shared = PostModel()
    }

    fun getAllPosts(callback: (List<Post>) -> Unit) {
        firebaseModel.getAllPosts(callback)
    }

    fun addPost(post: Post, callback: () -> Unit) {
        firebaseModel.addPost(post, callback)
    }
}