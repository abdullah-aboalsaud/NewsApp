package com.example.newsapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.models.headlines.Source
import com.example.domain.models.news.Article
import com.example.domain.utils.Result
import com.example.newsapp.R
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.ui.adapters.ArticlesAdapter
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
    private val articlesAdapter = ArticlesAdapter()
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
        viewModel.getHeadLines(GENERAL_CATEGORY)
        viewModel.getNewsSources(GENERAL_CATEGORY)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpCategoryAdapterWithClick()
        setUpHeadLineAdapterWithClick()
        setUpArticlesAdapterWithClick()
        setUpSourcesAdapterWithClick()
        observe()
    }

    override fun onResume() {
        super.onResume()
        showBottomNav()
    }

    private fun setUpCategoryAdapterWithClick() {
        binding.rvCategory.adapter = categoryAdapter

        categoryAdapter.onItemClick = { categoryTitle ->
            viewModel.getHeadLines(categoryTitle)
            viewModel.getNewsSources(categoryTitle)
        }


    }

    private fun setUpSourcesAdapterWithClick() {
        binding.rvSources.adapter = sourcesAdapter
        sourcesAdapter.onItemClick = SourcesAdapter.OnItemClick { source ->
            viewModel.getArticlesBySourceId(source.id ?: "")
        }

    }

    private fun setUpHeadLineAdapterWithClick() {
        binding.rvHeadline.adapter = headLinesAdapter

        headLinesAdapter.onItemClick = { article ->
            findNavController()
                .navigate(HomeFragmentDirections.actionGlobalToDetailsFragment(article))
        }
    }

    private fun setUpArticlesAdapterWithClick() {
        binding.rvArticles.adapter = articlesAdapter
        articlesAdapter.onItemClick = { article ->
            findNavController()
                .navigate(HomeFragmentDirections.actionGlobalToDetailsFragment(article))
        }
    }

    private fun observe() {
        viewModel.headLinesLiveData.observe(viewLifecycleOwner) { result ->

            when (result) {
                is Result.Loading -> {
                    showSourcesLoading()
                }

                is Result.Success -> {
                    hideSourcesLoading()
                    showHeadLines(result.data)
                }

                is Result.Error -> {
                    hideSourcesLoading()
                    // handel error
                }

                else -> Unit
            }

        }
        viewModel.articlesBySourceIdLiveData.observe(viewLifecycleOwner) { result ->

            when (result) {
                is Result.Loading -> {
                    //showArticlesLoading()
                }

                is Result.Success -> {
                    //hideArticlesLoading()
                    showArticlesBySourceId(result.data)
                }

                is Result.Error -> {
                    //hideArticlesLoading()
                    // handel error
                }

                else -> Unit
            }

        }
        viewModel.sourcesLiveData.observe(viewLifecycleOwner) { it ->
            when (it) {
                is Result.Loading -> {
                    showSourcesLoading()
                }

                is Result.Success -> {
                    hideSourcesLoading()
                    val firstSource = it.data?.get(0)?.id
                    viewModel.getArticlesBySourceId(firstSource.toString())
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

    private fun showArticlesBySourceId(articlesList: List<Article>?) {
        articlesAdapter.submitList(articlesList)
    }


    private fun setCategoryAdapterList() {
        categoriesList.add(CategoryModel(GENERAL_CATEGORY))
        categoriesList.add(CategoryModel(SPORT_CATEGORY))
        categoriesList.add(CategoryModel(TECHNOLOGY_CATEGORY))
        categoriesList.add(CategoryModel(SCIENCE_CATEGORY))
        categoriesList.add(CategoryModel(HEALTH_CATEGORY))
        categoriesList.add(CategoryModel(ENTERTAINMENT_CATEGORY))
        categoriesList.add(CategoryModel(BUSINESS_CATEGORY))
        // submit the list to adapter
        categoryAdapter.submitList(categoriesList)
    }


}