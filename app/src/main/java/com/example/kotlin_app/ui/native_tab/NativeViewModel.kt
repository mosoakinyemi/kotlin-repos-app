package com.example.kotlin_app.ui.native_tab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.kotlin_app.data.data.ReposRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class NativeViewModel @Inject constructor(
    repository: ReposRepository
) : ViewModel() {

    val currentQuery = MutableStateFlow<String?>("a")
    private val searchResults = currentQuery.flatMapLatest { query ->
        query?.let {
            repository.getRepos(query)
        } ?: emptyFlow()
    }

    fun onSearchQuerySubmit(query: String) {
        currentQuery.value = query
    }

    val repos = searchResults.asLiveData()
}

