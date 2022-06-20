package com.example.kotlin_app.data.services

import com.example.kotlin_app.data.models.SearchResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("search/repositories?q=a")
    fun searchRepos(): Call<SearchResponse>
}