package com.example.kotlin_app.data.models

data class Item(
    val created_at: String,
    val description: String,
    val full_name: String,
    val id: Int,
    val name: String,
    val owner: Owner,
    val stargazers_count: Int
)