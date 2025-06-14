package com.example.tema2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tema2.data.remote.PostRepository
import com.example.tema2.data.models.UiPost
import kotlinx.coroutines.launch

class FeedViewModel(private val repository: PostRepository) : ViewModel() {

    private val _posts = MutableLiveData<List<UiPost>>()
    val posts: LiveData<List<UiPost>> = _posts

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getPosts()
                _posts.value = result
            } catch (e: Exception) {
                _posts.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}