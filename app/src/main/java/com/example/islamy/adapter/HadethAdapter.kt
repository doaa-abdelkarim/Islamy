package com.example.islamy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.holyquran.R
import com.example.islamy.model.Hadeth
import kotlinx.android.synthetic.main.chapter_ahadeth_list_item.view.*

class HadethAdapter(val ahadeth: List<Hadeth>?)
    : RecyclerView.Adapter<HadethAdapter.ChapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.chapter_ahadeth_list_item, parent, false)
        return ChapterViewHolder(view)
    }

    override fun getItemCount() = ahadeth?.size ?: 0

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        holder.textViewSentence.text = ahadeth?.get(position)?.text?: ""
    }


    class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewSentence = itemView.text_view_content
    }
}