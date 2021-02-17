package com.example.islamy.ui

import com.example.islamy.api.MP3QuraanAPIManager
import com.example.islamy.model.Radio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RadioChannels {

    fun getRadioChannelsList(onResponseListener: RadioChannelsView.OnResponseListener) {
        val call = MP3QuraanAPIManager
            .getMP3QuraanAPI()
            .getRadioChannels()
        call.enqueue(object : Callback<Radio> {
            override fun onFailure(call: Call<Radio>, t: Throwable) {
                onResponseListener.onFailure(t)
            }

            override fun onResponse(call: Call<Radio>, response: Response<Radio>) {
                    onResponseListener.onResponse(response)
            }
        })
    }
}