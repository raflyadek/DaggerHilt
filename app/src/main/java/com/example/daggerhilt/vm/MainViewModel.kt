package com.example.daggerhilt.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerhilt.data.User
import com.example.daggerhilt.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    val state = MutableStateFlow<com.example.daggerhilt.State>(com.example.daggerhilt.State.START)

    init {
        loadUserList()
    }
    fun loadUserList() = viewModelScope.launch {
            state.value = com.example.daggerhilt.State.LOADING
            try {
                val userList = withContext(Dispatchers.IO) {
                    apiService.getMovies() }
                state.value = com.example.daggerhilt.State.SUCCESS(userList)
            } catch (e: Exception) {
                state.value = com.example.daggerhilt.State.FAILURE(e.localizedMessage)
            }
        }
}