package com.example.tema2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tema2.data.remote.PostRepository

class FeedViewModelFactory(private val repository: PostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FeedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}