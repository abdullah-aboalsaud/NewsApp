package com.example.newsapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.domain.models.headlines.Source
import com.example.domain.models.news.Article
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.ui.adapters.HeadLinesAdapter
import com.example.newsapp.ui.adapters.SourcesAdapter
import com.example.newsapp.ui.view_models.NewsViewModel
import com.example.newsapp.utils.showBottomNav
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, NewsViewModel>() {
    val args by navArgs<HomeFragmentArgs>()
    private val newsViewModel by viewModels<NewsViewModel>()
    private val headLinesAdapter = HeadLinesAdapter()
    private val sourcesAdapter = SourcesAdapter()


    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initViewModel(): NewsViewModel {
        return newsViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHeadLines(args.categoryTitle)
        viewModel.getNewsSources(args.categoryTitle)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNav()
        setUpHeadLineAdapterWithClick()
        setUpSourcesAdapterWithClick()
        observe()
    }

    private fun setUpSourcesAdapterWithClick() {
        binding.rvSources.adapter = sourcesAdapter

    }

    private fun setUpHeadLineAdapterWithClick() {
        binding.rvHeadline.adapter = headLinesAdapter

        headLinesAdapter.onItemClick = HeadLinesAdapter.OnItemClick { article ->
            // navigate to Fragment details
        }


    }

    private fun observe() {
        viewModel.newsLiveData.observe(viewLifecycleOwner) { articlesList ->
            showHeadLines(articlesList)
        }
        viewModel.sourcesLiveData.observe(viewLifecycleOwner) { sourcesList ->
            showSources(sourcesList)
        }
    }

    private fun showSources(sourcesList: List<Source>?) {
        sourcesAdapter.submitList(sourcesList)
    }

    private fun showHeadLines(articlesList: List<Article>?) {
        headLinesAdapter.submitList(articlesList)
    }


}