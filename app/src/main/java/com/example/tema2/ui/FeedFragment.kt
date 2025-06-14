package com.example.tema2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tema2.data.AppDatabase
import com.example.tema2.data.PostRepository
import com.example.tema2.data.remote.RetrofitClient
import com.example.tema2.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FeedViewModel
    private val postAdapter = PostAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDependencies()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupDependencies() {
        val apiService = RetrofitClient.api
        val dao = AppDatabase.getDatabase(requireContext().applicationContext).appDao()
        val repository = PostRepository(apiService, dao)
        val factory = FeedViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[FeedViewModel::class.java]
    }

    private fun setupRecyclerView() {
        binding.recyclerViewFeed.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            postAdapter.submitList(posts)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBarFeed.isVisible = isLoading
            binding.recyclerViewFeed.isVisible = !isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}