package com.example.hse_android_lab3

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything")
    suspend fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("q") query: String
    ): NewsResponse
}
