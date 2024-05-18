package com.example.daggerhilt

import com.example.daggerhilt.data.User

sealed class State {
    object START: State()
    object LOADING: State()
    data class SUCCESS(val users: List<User>): State()
    data class FAILURE(val message: String): State()
}
