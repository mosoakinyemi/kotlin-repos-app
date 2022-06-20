package com.example.kotlin_app.data.services

import android.content.Context
import com.example.kotlin_app.data.models.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.github.com/"

class RequestManager(context:Context) {
    var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getSearchResults(listener: SearchResponseListener){
        val search = retrofit.create(ApiInterface::class.java).searchRepos()
        search.enqueue(object: Callback<SearchResponse>{
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
               if(!response.isSuccessful){
                   listener.didError(response.message())
                   return
               }
                response.body()?.let { listener.didFetch(it, response.message()) }
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                t.message?.let { listener.didError(it) }
            }
        })
    }

}