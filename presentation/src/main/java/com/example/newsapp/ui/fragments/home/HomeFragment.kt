package com.example.newsapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.domain.models.headlines.Source
import com.example.domain.models.news.Article
import com.example.domain.utils.Result
import com.example.newsapp.R
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.ui.adapters.HeadLinesAdapter
import com.example.newsapp.ui.adapters.SourcesAdapter
import com.example.newsapp.ui.adapters.CategoriesAdapter
import com.example.newsapp.ui.models.CategoryModel
import com.example.newsapp.ui.view_models.NewsViewModel
import com.example.newsapp.utils.BUSINESS_CATEGORY
import com.example.newsapp.utils.ENTERTAINMENT_CATEGORY
import com.example.newsapp.utils.GENERAL_CATEGORY
import com.example.newsapp.utils.HEALTH_CATEGORY
import com.example.newsapp.utils.SCIENCE_CATEGORY
import com.example.newsapp.utils.SPORT_CATEGORY
import com.example.newsapp.utils.TECHNOLOGY_CATEGORY
import com.example.newsapp.utils.showBottomNav
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, NewsViewModel>() {
    private val newsViewModel by viewModels<NewsViewModel>()
    val categoryAdapter = CategoriesAdapter()
    private val headLinesAdapter = HeadLinesAdapter()
    private val sourcesAdapter = SourcesAdapter()
    private var categoriesList = mutableListOf<CategoryModel>()


    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initViewModel(): NewsViewModel {
        return newsViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCategoryAdapterList()
        viewModel.getHeadLines(SPORT_CATEGORY)
        viewModel.getNewsSources(SPORT_CATEGORY)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpCategoryAdapterWithClick()
        setUpHeadLineAdapterWithClick()
        setUpSourcesAdapterWithClick()
        observe()
    }

    private fun setUpCategoryAdapterWithClick() {
        binding.rvCategory.adapter = categoryAdapter
        categoryAdapter.onItemClick = CategoriesAdapter.OnItemClick { categoryTitle ->
            viewModel.getHeadLines(categoryTitle)
            viewModel.getNewsSources(categoryTitle)

        }
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
        viewModel.sourcesLiveData.observe(viewLifecycleOwner) { it ->
            when (it) {
                is Result.Loading -> {
                      showSourcesLoading()
                }

                is Result.Success -> {
                    hideSourcesLoading()
                    showSources(it.data)
                }

                is Result.Error -> {
                   hideSourcesLoading()
                }

                else -> Unit
            }

        }

    }

    private fun showSourcesLoading() {
        binding.progressSources.visibility = View.VISIBLE
    }
    private fun hideSourcesLoading() {
        binding.progressSources.visibility = View.GONE
    }

    private fun showSources(sourcesList: List<Source>?) {
        sourcesAdapter.submitList(sourcesList)
    }

    private fun showHeadLines(articlesList: List<Article>?) {
        headLinesAdapter.submitList(articlesList)
    }


    private fun setCategoryAdapterList() {
        categoriesList.add(
            CategoryModel(SPORT_CATEGORY, R.color.primary_red)
        )
        categoriesList.add(
            CategoryModel(TECHNOLOGY_CATEGORY, R.color.primary_red)
        )
        categoriesList.add(
            CategoryModel(SCIENCE_CATEGORY, R.color.primary_red)
        )
        categoriesList.add(
            CategoryModel(HEALTH_CATEGORY, R.color.primary_red)
        )
        categoriesList.add(
            CategoryModel(GENERAL_CATEGORY, R.color.primary_red)
        )
        categoriesList.add(
            CategoryModel(ENTERTAINMENT_CATEGORY, R.color.primary_red)
        )
        categoriesList.add(
            CategoryModel(BUSINESS_CATEGORY, R.color.primary_red)
        )
        categoryAdapter.submitList(categoriesList)

    }


}