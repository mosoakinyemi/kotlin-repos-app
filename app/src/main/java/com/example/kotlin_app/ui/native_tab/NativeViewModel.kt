package com.example.kotlin_app.ui.native_tab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.kotlin_app.data.data.ReposRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NativeViewModel @Inject constructor(
    repository: ReposRepository
) : ViewModel() {

    val repos = repository.getRepos().asLiveData()
}