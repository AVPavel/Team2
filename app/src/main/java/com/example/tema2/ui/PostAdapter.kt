package com.example.tema2.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tema2.data.models.UiPost
import com.example.tema2.databinding.ListItemPostBinding

class PostAdapter : ListAdapter<UiPost, PostAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ListItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class PostViewHolder(private val binding: ListItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(uiPost: UiPost) {
            binding.textViewFullName.text = uiPost.user.name
            val addressText = "${uiPost.user.address.street}, ${uiPost.user.address.suite}"
            binding.textViewAddress.text = addressText
            binding.textViewPostTitle.text = uiPost.post.title
            binding.textViewPostMessage.text = uiPost.post.body
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<UiPost>() {
        override fun areItemsTheSame(oldItem: UiPost, newItem: UiPost): Boolean {
            return oldItem.post.id == newItem.post.id
        }

        override fun areContentsTheSame(oldItem: UiPost, newItem: UiPost): Boolean {
            return oldItem == newItem
        }
    }
}