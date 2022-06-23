package com.example.kotlin_app.data.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlin_app.data.models.RepoItem
import dagger.Provides

@Database(entities = [RepoItem::class],version = 2)
abstract class ReposDatabase:RoomDatabase() {
    abstract fun reposDao(): ReposDao
}