package com.example.islamy.model

import com.google.gson.annotations.SerializedName

data class Radio(
    val radios: List<Channel>
)

data class Channel(
    val name: String? = null,
    @SerializedName("radio_url")
    val url: String? = null,
    var isSelected: Boolean = false
)