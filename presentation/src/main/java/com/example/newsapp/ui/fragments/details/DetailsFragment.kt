package com.example.newsapp.ui.fragments.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.databinding.FragmentDetailsBinding
import com.example.newsapp.ui.view_models.FavoriteViewModel

import com.example.newsapp.utils.hideBottomNav
import com.example.newsapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding, FavoriteViewModel>() {
    val args by navArgs<DetailsFragmentArgs>()
    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    override fun inflateBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentDetailsBinding.inflate(inflater, container, false)

    override fun initViewModel(): FavoriteViewModel {
        return favoriteViewModel
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNav()
        checkIfArticleIsFavorite()
        initViews()
        onclicks()
        observers()
    }

    private fun observers() {
        viewModel.isFavoriteAdded.observe(viewLifecycleOwner) { isAdded ->
            if (isAdded) {
                binding.btnAddToFav.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.primary_red)
                )
                showToast("Added")
            } else {
                binding.btnAddToFav.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.gray)
                )
            }
        }

    }

    private fun checkIfArticleIsFavorite() {
        // Check if the article is already added to favorites
        favoriteViewModel.checkIfArticleInFavorites(args.Article.url.toString())
    }

    private fun onclicks() {

        binding.btnAddToFav.setOnClickListener {
            val article = args.Article
            favoriteViewModel.addArticle(article)
        }

        binding.tvUrl.setOnClickListener {
            intentBrowser()
        }

    }

    private fun intentBrowser() {
        val url = args.Article.url
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(intent)
    }

    private fun initViews() {
        val urlText = args.Article.url
        // Underline the text
        val underlinedText = SpannableString(urlText ?: "")
        underlinedText.setSpan(UnderlineSpan(), 0, underlinedText.length, 0)

        binding.apply {
            collapsingToolBar.title = args.Article.author
            tvArticleTitle.text = args.Article.title
            tvDescription.text = args.Article.description
            tvUrl.text = underlinedText
            tvContent.text = args.Article.content

            Glide.with(binding.root.context)
                .load(args.Article.urlToImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivArticleImage)
        }
    }

}