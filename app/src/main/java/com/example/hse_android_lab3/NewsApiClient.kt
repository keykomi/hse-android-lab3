package com.example.hse_android_lab3

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiClient {
    private val retrofit: Retrofit
    private val newsApiService: NewsApiService

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://newsdata.io/api/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsApiService = retrofit.create(NewsApiService::class.java)
    }

    suspend fun getNews(apiKey: String, query: String): NewsResponse {
        return newsApiService.getNews(apiKey, query)
    }
}
