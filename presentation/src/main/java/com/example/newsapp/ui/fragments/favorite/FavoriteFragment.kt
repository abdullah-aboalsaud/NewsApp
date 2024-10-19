package com.example.newsapp.ui.fragments.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.domain.models.news.Article
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentFavoriteBinding
import com.example.newsapp.ui.adapters.FavoritesAdapter
import com.example.newsapp.ui.view_models.FavoriteViewModel
import com.example.newsapp.utils.showDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {
    private val favoriteViewModel by viewModels<FavoriteViewModel>()
    private val favoriteAdapter = FavoritesAdapter()

    override fun inflateBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentFavoriteBinding.inflate(inflater, container, false)

    override fun initViewModel(): FavoriteViewModel {
        return favoriteViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFavoriteArticles()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFavoriteAdapterWithClick()
        onClicks()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.articles.collect { articles ->
                    showFavoritesArticles(articles)
                }
            }
        }
    }

    private fun onClicks() {
        binding.ivBtnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    fun showFavoritesArticles(articles: List<Article>) {
        favoriteAdapter.submitList(articles)
    }

    private fun setUpFavoriteAdapterWithClick() {
        binding.rvFavoriteArticles.adapter = favoriteAdapter

        favoriteAdapter.onItemClick = { article ->
            findNavController()
                .navigate(FavoriteFragmentDirections.actionGlobalToDetailsFragment(article))
        }

        // to delete item
        favoriteAdapter.onLongItemClick = { article ->
            context?.showDialog(
                message = "are you sure you want to delete!",
                onPositiveClick = { viewModel.deleteArticle(article) })
        }

    }


}