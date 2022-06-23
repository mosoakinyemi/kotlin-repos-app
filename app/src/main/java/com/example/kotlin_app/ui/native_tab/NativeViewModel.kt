package com.example.kotlin_app.ui.native_tab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.kotlin_app.data.data.ReposRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

const val DEFAULT_QUERY = "a"

@HiltViewModel
class NativeViewModel @Inject constructor(
    repository: ReposRepository
) : ViewModel() {

    val searchQuery = MutableStateFlow<String>(DEFAULT_QUERY)
    val sortOrder = MutableStateFlow(SortOrder.BY_ASC  )
    private val searchResults = combine(
        searchQuery,
        sortOrder
    ){query,sortOrder -> Pair(query,sortOrder)}
        .flatMapLatest { (query,sortOrder) ->
        query?.let {
            repository.getRepos(query,sortOrder)
        } ?: emptyFlow()
    }

    fun onSearchQuerySubmit(query: String) {
        searchQuery.value = query
    }

    val repos = searchResults.asLiveData()
}

enum class SortOrder {BY_ASC, BY_DESC}

