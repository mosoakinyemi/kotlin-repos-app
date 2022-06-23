package com.example.kotlin_app.data.data

import android.util.Log
import androidx.room.withTransaction
import com.example.kotlin_app.data.api.ReposApi
import com.example.kotlin_app.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class ReposRepository @Inject constructor(
    private val api: ReposApi,
    private val db: ReposDatabase
) {
    private val reposDao = db.reposDao()

    fun getRepos() = networkBoundResource(
        query = {
            reposDao.getAllRepos()
        },
        fetch = {
            delay(1000)
            Log.d("FETCHEING REPOS","HERE")
            api.searchRepos()
        },
        saveFetchResult = { response ->
            db.withTransaction {
                Log.d("FETCHEd REPOS 1","HERE")
                reposDao.deleteAllRepos()
                reposDao.insertRepos(response.items)
                Log.d("FETCHED REPOS", response.total_count?.toString())

            }
        }
    )
}