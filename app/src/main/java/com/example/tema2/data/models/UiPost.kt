package com.example.tema2.data.models

import androidx.room.Embedded

data class UiPost (
    @Embedded
    val post: Post,
    @Embedded
    val user: User
)