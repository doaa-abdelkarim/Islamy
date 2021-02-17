package com.example.islamy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.holyquran.R
import kotlinx.android.synthetic.main.index_list_item.view.*


class IndexAdapter(val chapters: Array<String>?) :
    RecyclerView.Adapter<IndexAdapter.IndexViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndexViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.index_list_item, parent, false)

        val params = view.layoutParams
        params.width = parent.measuredWidth / 3
        params.height = parent.measuredHeight / 3
        view.layoutParams = params

        return IndexViewHolder(view, onItemClickListener)
    }

    override fun getItemCount() = chapters?.size ?: 0

    override fun onBindViewHolder(holder: IndexViewHolder, position: Int) {
        holder.textViewChapterName.text = chapters?.get(position) ?: ""
    }

    class IndexViewHolder(itemView: View, listener: OnItemClickListener?) :
        RecyclerView.ViewHolder(itemView) {
        val textViewChapterName: TextView = itemView.text_view_chapter_name

        init {
            itemView.setOnClickListener {
                listener?.apply {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION)
                        onItemClick(position)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}