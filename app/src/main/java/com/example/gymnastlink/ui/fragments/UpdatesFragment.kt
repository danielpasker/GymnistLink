package com.example.gymnastlink.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymnastlink.R
import com.example.gymnastlink.model.Post
import com.example.gymnastlink.ui.MainActivity
import com.example.gymnastlink.ui.adapters.PostAdapter
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.time.LocalDate

class UpdatesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostAdapter
    private lateinit var newPostFab: ExtendedFloatingActionButton
    private lateinit var postsActivityLauncher: ActivityResultLauncher<Intent>

    companion object {
        val postList = mutableListOf<Post>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_updates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setFragmentTitle(getString(R.string.updates))

        recyclerView = view.findViewById(R.id.post_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        newPostFab = view.findViewById<ExtendedFloatingActionButton>(R.id.new_post_fab).apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_updatesFragment_to_newPostFragment)
            }
        }

        postsActivityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    adapter.notifyDataSetChanged()
                }
            }

        // TODO: delete when handling data from a real source
        if (postList.isEmpty()) {
            postList.add(
                Post(
                    "John Smith",
                    "Heavy Lifter",
                    "Post Title 1",
                    "This is the first post content.",
                    null,
                    12,
                    LocalDate.of(2024, 11, 27)
                )
            )
            postList.add(
                Post(
                    "Jane Doe",
                    "Runner",
                    "Post Title 2",
                    "This is the second post content.",
                    null,
                    0,
                    LocalDate.of(2024, 11, 27)
                )
            )
            postList.add(
                Post(
                    "Alex Kim",
                    "Cyclist",
                    "Post Title 3",
                    "This is the third post content.",
                    null,
                    2,
                    LocalDate.of(2024, 9, 25)
                )
            )
        }

        adapter = PostAdapter(postList)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}