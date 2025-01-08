package com.example.gymnastlink.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymnastlink.R
import com.example.gymnastlink.model.Comment
import com.example.gymnastlink.ui.MainActivity
import com.example.gymnastlink.ui.adapters.CommentAdapter

class PostCommentsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CommentAdapter
    private val commentList = mutableListOf<Comment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_comments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as? MainActivity
        mainActivity?.setFragmentTitle(getString(R.string.comments))
        mainActivity?.showBottomNavigation(false)
        mainActivity?.showReturnButtonOnToolbar(true)

        recyclerView = view.findViewById(R.id.post_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize the comment list with some dummy data
        commentList.add(Comment("John Doe", "This is a comment."))
        commentList.add(Comment("Jane Smith", "This is another comment."))
        commentList.add(Comment("Jane Smith", "This is another comment."))
        commentList.add(Comment("Jane Smith", "This is another comment."))

        adapter = CommentAdapter(commentList)
        recyclerView.adapter = adapter
    }
}