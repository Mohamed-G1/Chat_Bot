package com.example.chatbot.network

import com.example.chatbot.model.BotResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("get")
    suspend fun getBrainAIMessage(
        @Query("bid") bid: Int,
        @Query("key") key: String,
        @Query("uid") uid: String,
        @Query("msg") msg:String,
    ): Response<BotResponse>
}