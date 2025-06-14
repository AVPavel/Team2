package com.example.tema2.data.remote

import com.example.tema2.data.models.Post
import com.example.tema2.data.models.User
import retrofit2.http.GET


interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("posts")
    suspend fun getPosts(): List<Post>
}