package com.example.kotlin_app.data.models

data class SearchResponse(
    val incomplete_results: Boolean,
    val items: List<RepoItem>,
    val total_count: Int
)