package com.example.kotlin_app.data.data

import androidx.room.*
import com.example.kotlin_app.data.models.RepoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ReposDao {
    @Query("SELECT * FROM repos")
    fun getAllRepos(): Flow<List<RepoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repos: List<RepoItem>)

    @Query("DELETE FROM repos")
    suspend fun deleteAllRepos()

}