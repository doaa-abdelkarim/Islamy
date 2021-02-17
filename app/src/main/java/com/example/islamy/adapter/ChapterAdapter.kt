package com.example.islamy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.holyquran.R
import kotlinx.android.synthetic.main.chapter_ahadeth_list_item.view.*

class ChapterAdapter(val sentences: List<String>?)
    : RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.chapter_ahadeth_list_item, parent, false)
        return ChapterViewHolder(view)
    }

    override fun getItemCount() = sentences?.size ?: 0

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        holder.textViewSentence.text = sentences?.get(position) ?: ""
    }


    class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewSentence = itemView.text_view_content
    }
}