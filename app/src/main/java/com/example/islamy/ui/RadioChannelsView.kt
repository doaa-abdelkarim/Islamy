package com.example.islamy.ui

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.holyquran.R
import com.example.islamy.adapter.RadioAdapter
import com.example.islamy.model.Channel
import com.example.islamy.model.Radio
import kotlinx.android.synthetic.main.radio_list.*
import retrofit2.Response


class RadioChannelsView : Fragment() {

    var selectedPosition = -1
    val radioAdapter = RadioAdapter(null)
    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RadioChannels().getRadioChannelsList(object : OnResponseListener {
            override fun onFailure(t: Throwable) {
                Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(response: Response<Radio>) {
                if (!response.isSuccessful) {
                    Toast
                        .makeText(activity, "Code: ${response.code()}", Toast.LENGTH_SHORT)
                        .show();
                } else {
                    progress_bar.visibility = ProgressBar.GONE
                    setupRadioAdapter(response.body()?.radios)
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.radio_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildRadioChannelsRecyclerView()
    }

    override fun onStop() {
        super.onStop()
        releaseMediaPlayer()
    }

    private fun setupRadioAdapter(channels: List<Channel>?) {
        radioAdapter.swapChannelsList(channels)
        radioAdapter.onItemClickListener = object : RadioAdapter.OnItemClickListener {
            override fun onPlay(position: Int) {
                selectedPosition = position
                if (mediaPlayer != null)
                    releaseMediaPlayer()
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(
                        context!!,
                        Uri.parse(radioAdapter.channels?.get(position)?.url)
                    )
                    prepareAsync()
                    setOnPreparedListener { it.start() }
                }
            }

            override fun onStop(position: Int) {
                if (position == selectedPosition)
                    releaseMediaPlayer()
            }

        }
    }

    private fun releaseMediaPlayer() {
        mediaPlayer?.apply {
            if (isPlaying)
                stop()
            release()
        }
        mediaPlayer = null
    }

    private fun buildRadioChannelsRecyclerView() {
        val recyclerViewRadioChannel = recycler_view_radio_channels
        recyclerViewRadioChannel.apply {
            adapter = radioAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
        PagerSnapHelper()
            .attachToRecyclerView(recyclerViewRadioChannel)
    }


    companion object {
        @JvmStatic
        fun newInstance() = RadioChannelsView()
    }

    interface OnResponseListener {
        fun onFailure(t: Throwable)
        fun onResponse(response: Response<Radio>)
    }
}