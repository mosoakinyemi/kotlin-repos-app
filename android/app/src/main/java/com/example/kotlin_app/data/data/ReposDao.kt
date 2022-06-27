package com.example.kotlin_app.data.data

import androidx.room.*
import com.example.kotlin_app.data.models.RepoItem
import com.example.kotlin_app.ui.native_tab.SortOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReposDao {

    fun getRepos(query: String, sortOrder: SortOrder): Flow<List<RepoItem>> =
        when (sortOrder) {
            SortOrder.BY_ASC -> getReposSortedByAsc(query)
            SortOrder.BY_DESC -> getReposSortedByDesc(query)
        }

    @Query("SELECT * FROM repos WHERE name LIKE '%' || :searchQuery || '%' ORDER BY name DESC")
    fun getReposSortedByAsc(searchQuery: String): Flow<List<RepoItem>>

    @Query("SELECT * FROM repos WHERE name LIKE '%' || :searchQuery || '%' ORDER BY name ASC")
    fun getReposSortedByDesc(searchQuery: String): Flow<List<RepoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repos: List<RepoItem>)

    @Query("DELETE FROM repos")
    suspend fun deleteAllRepos()

}