package com.example.newsapp.ui.fragments.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentFavoriteBinding
import com.example.newsapp.ui.view_models.FavoriteViewModel
import com.example.newsapp.ui.view_models.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {
    private val favoriteViewModel by viewModels<FavoriteViewModel>()


    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?
    )= FragmentFavoriteBinding.inflate(inflater,container,false)

    override fun initViewModel(): FavoriteViewModel { return favoriteViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFavoriteArticles()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.articles.collect { articles ->
                    // Update your RecyclerView adapter with the articles list
                }

            }

        }
    }


}