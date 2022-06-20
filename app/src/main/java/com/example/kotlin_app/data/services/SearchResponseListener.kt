package com.example.kotlin_app.data.services

import com.example.kotlin_app.data.models.SearchResponse


interface SearchResponseListener {
  fun didFetch(response: SearchResponse, message:String)
  fun didError(message: String)
}