package com.example.kotlin_app.data.api

import com.example.kotlin_app.data.models.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReposApi {
    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("search/repositories")
    suspend fun searchRepos(@Query("q") query: String="a"):SearchResponse
}

