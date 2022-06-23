package com.example.kotlin_app.data.data

import android.util.Log
import androidx.room.withTransaction
import com.example.kotlin_app.data.api.ReposApi
import com.example.kotlin_app.ui.native_tab.SortOrder
import com.example.kotlin_app.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class ReposRepository @Inject constructor(
    private val api: ReposApi,
    private val db: ReposDatabase
) {
    private val reposDao = db.reposDao()

    fun getRepos(searchQuery: String,sortOrder: SortOrder) = networkBoundResource(
        query = {
            reposDao.getRepos(searchQuery,sortOrder)
        },
        fetch = {
            delay(1000)
            api.searchRepos(searchQuery)
        },
        saveFetchResult = { response ->
            db.withTransaction {
                reposDao.deleteAllRepos()
                reposDao.insertRepos(response.items)
            }
        }
    )
}