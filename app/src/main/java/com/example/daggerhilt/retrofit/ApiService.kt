package com.example.daggerhilt.retrofit

import com.example.daggerhilt.data.User
import retrofit2.http.GET

interface ApiService {
    @GET("movielist.json")
    suspend fun getMovies(): List<User>
}