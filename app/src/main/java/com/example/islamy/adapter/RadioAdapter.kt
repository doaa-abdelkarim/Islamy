package com.example.islamy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.holyquran.R
import com.example.islamy.model.Channel
import kotlinx.android.synthetic.main.radio_list_item.view.*


class RadioAdapter(var channels: List<Channel>?) :
    RecyclerView.Adapter<RadioAdapter.RadioViewHolder>() {

    var selectedItemIndex = -1
    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.radio_list_item, parent, false)

        return RadioViewHolder(view)
    }

    override fun getItemCount() = channels?.size ?: 0

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        holder.textViewChannelName.text = channels?.get(position)?.name?: ""

        if (channels?.get(position)?.isSelected!!)
            holder.textViewChannelName.setTextColor(
                (ContextCompat.getColor(holder.itemView.context, R.color.colorAccent))
            )
        else
            holder.textViewChannelName.setTextColor(
                (ContextCompat.getColor(holder.itemView.context, R.color.colorTextColor))
            )
    }

    inner class RadioViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val textViewChannelName: TextView = itemView.tex_view_channel_name
        val imageViewPlay = itemView.image_view_play
        val imageViewStop = itemView.image_view_stop

        init {
            imageViewPlay.setOnClickListener {
                onItemClickListener?.apply {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        channels?.get(position)?.isSelected = true
                        if (selectedItemIndex != -1)
                            channels?.get(selectedItemIndex)?.isSelected = false
                        selectedItemIndex = position
                        notifyDataSetChanged()

                        onPlay(position)
                    }
                }
            }

            imageViewStop.setOnClickListener {
                onItemClickListener?.apply {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        channels?.get(position)?.isSelected = false
                        selectedItemIndex = -1
                        notifyDataSetChanged()

                        onStop(position)
                    }
                }
            }
        }
    }

    fun swapChannelsList(channels: List<Channel>?) {
        this.channels = channels
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onPlay(position: Int)
        fun onStop(position: Int)
    }
}