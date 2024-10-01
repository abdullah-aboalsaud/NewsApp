package com.example.newsapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.domain.models.news.Article
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.ui.adapters.HeadLinesAdapter
import com.example.newsapp.ui.fragments.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, NewsViewModel>() {
    private val newsViewModel by viewModels<NewsViewModel>()
    private val headLinesAdapter = HeadLinesAdapter()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initViewModel(): NewsViewModel {
        return newsViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHeadLines("en")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapterWithClick()
        observe()
    }

    private fun setUpAdapterWithClick() {
        binding.rvHeadline.adapter = headLinesAdapter

    }

    private fun observe() {
        viewModel.newsLiveData.observe(viewLifecycleOwner) { articles ->
            showHeadLines(articles)
        }
    }

    private fun showHeadLines(articles: List<Article>?) {

        headLinesAdapter.submitList(articles)

    }
}