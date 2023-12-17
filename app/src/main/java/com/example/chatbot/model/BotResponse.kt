package com.example.chatbot.model

import com.google.gson.annotations.SerializedName

data class BotResponse(
    @SerializedName("cnt")
    val cnt: String
)
