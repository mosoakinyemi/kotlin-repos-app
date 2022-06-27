package com.example.kotlin_app.data.models
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "repos")
data class RepoItem(
        val created_at: String,
        val description: String?,
        val full_name: String,
        @PrimaryKey val id: Int,
        val name: String,
        @Embedded val owner: Owner,
        val stargazers_count: Int
)