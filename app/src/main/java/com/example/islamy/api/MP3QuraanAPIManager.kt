package com.example.islamy.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MP3QuraanAPIManager {

    companion object {
        private var instance: Retrofit? = null

        @Synchronized
        private fun getInstance(): Retrofit {
            if (instance == null)
                instance = Retrofit
                    .Builder()
                    .baseUrl("http://api.mp3quran.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return instance!!
        }

        fun getMP3QuraanAPI() = getInstance().create(MP3QuraanAPI::class.java)
    }
}