package com.example.kotlin_app.utils

import android.content.ContentValues.TAG
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = channelFlow {
    val data = query().first()
    if (shouldFetch(data)) {
        val loading = launch {
            query().collect { send(Resource.Loading(it)) }
        }

        try {
            delay(2000)
            saveFetchResult(fetch())
            loading.cancel()
            query().collect {
                Log.d("FETCHED DATA1", it.toString())
                send(Resource.Success(it))
            }
        } catch (t: Throwable) {
            loading.cancel()
            query().collect {
                Log.d("FETCHED ERROR DATA", it.toString())
                send(Resource.Error(t, it))
            }
        }
    } else {
        query().collect {
            Log.d("FETCHED DATA 2", it.toString())
            send(Resource.Success(it))
        }
    }
}
