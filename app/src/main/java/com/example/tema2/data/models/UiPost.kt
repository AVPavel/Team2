package com.example.tema2.data.models

import androidx.room.Embedded

data class UiPost(
    @Embedded(prefix = "post_")
    val post: Post,

    @Embedded(prefix = "user_")
    val user: User
)