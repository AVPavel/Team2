package com.example.tema2.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tema2.data.models.Post
import com.example.tema2.data.models.UiPost
import com.example.tema2.data.models.User

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<Post>)

    @Query("SELECT * FROM posts INNER JOIN users ON posts.userId = users.id ORDER BY posts.id")
    suspend fun getAllPostsWithUsers(): List<UiPost>

    @Query("SELECT COUNT(*) FROM posts")
    suspend fun getPostCount(): Int
}