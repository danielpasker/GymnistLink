package com.example.gymnastlink.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymnastlink.R

class RecyclerWithTitleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val title: TextView
    val recyclerView: RecyclerView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_recycler_with_title, this, true)
        title = findViewById(R.id.title)
        recyclerView = findViewById(R.id.recycler_view)
    }
}