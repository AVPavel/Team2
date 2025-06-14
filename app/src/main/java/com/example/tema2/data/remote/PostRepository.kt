package com.example.tema2.data.remote

import com.example.tema2.data.AppDao
import com.example.tema2.data.models.UiPost

class PostRepository(
    private val apiService: ApiService,
    private val appDao: AppDao
) {

    suspend fun getPosts(): List<UiPost> {
        val postCountInDb = appDao.getPostCount()

        if (postCountInDb == 0) {
            val usersFromApi = apiService.getUsers()
            val postsFromApi = apiService.getPosts()

            appDao.insertUsers(usersFromApi)
            appDao.insertPosts(postsFromApi)
        }

        return appDao.getAllPostsWithUsers()
    }
}