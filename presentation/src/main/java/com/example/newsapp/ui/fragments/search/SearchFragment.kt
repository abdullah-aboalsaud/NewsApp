package com.example.newsapp.ui.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentSearchBinding
import com.example.newsapp.ui.adapters.ArticlesAdapter
import com.example.newsapp.ui.fragments.home.HomeFragmentDirections
import com.example.newsapp.ui.view_models.NewsViewModel
import com.example.newsapp.utils.hidePlaceHolder
import com.example.newsapp.utils.showPlaceHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, NewsViewModel>() {
    private val newsViewModel by viewModels<NewsViewModel>()
    private val articlesSearchAdapter = ArticlesAdapter()
    private var searchJob:Job?=null

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchBinding.inflate(inflater, container, false)
    override fun initViewModel(): NewsViewModel {
        return newsViewModel
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpArticlesAdapterWithClick()
        setUpSearchListener()
        observeSearchResults()
        observeLoadingState()
        onclick()
    }

    private fun onclick() {
        binding.ivBtnBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }


    private fun setUpArticlesAdapterWithClick() {
        binding.rvSearch.adapter = articlesSearchAdapter
        articlesSearchAdapter.onItemClick = { article ->
            findNavController()
                .navigate(HomeFragmentDirections.actionGlobalToDetailsFragment(article))
        }
    }

    private fun setUpSearchListener() {
        binding.etSearch.doAfterTextChanged { searchText ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch() {
                delay(500) // we Add delay to prevent unnecessary API calls
                if (!searchText.isNullOrBlank()) {
                    newsViewModel.searchForNews(searchText.toString())
                }else{
                    articlesSearchAdapter.submitList(listOf())
                }
            }
        }
    }

    private fun observeSearchResults() {
        newsViewModel.searchLiveData.observe(viewLifecycleOwner) { articles ->
            if (articles.isNullOrEmpty()) {
                showPlaceHolder(binding.rvSearch,binding.tvArticlesPlaceholder)
            } else {
                hidePlaceHolder(binding.rvSearch,binding.tvArticlesPlaceholder)
                articlesSearchAdapter.submitList(articles)
            }
        }
    }

    private fun observeLoadingState() {
        newsViewModel.isLoadingArticles.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressArticles.visibility = View.VISIBLE
            } else {
                binding.progressArticles.visibility = View.GONE
            }
        }
    }






}