package com.example.kotlin_app.data.models

import androidx.room.ColumnInfo

data class Owner(
    val avatar_url: String,
    val login: String,
    @ColumnInfo(name = "owner_id") val id: Int
)