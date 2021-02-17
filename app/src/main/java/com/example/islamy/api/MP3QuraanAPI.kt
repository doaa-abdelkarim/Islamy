package com.example.islamy.api

import com.example.islamy.model.Radio
import retrofit2.Call
import retrofit2.http.GET

interface MP3QuraanAPI {
    @GET("radios/radio_arabic.json")
    fun getRadioChannels(): Call<Radio>
}