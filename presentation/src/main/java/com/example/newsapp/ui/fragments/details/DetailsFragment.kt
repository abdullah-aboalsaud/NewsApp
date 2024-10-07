package com.example.newsapp.ui.fragments.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.databinding.FragmentDetailsBinding

import com.example.newsapp.utils.hideBottomNav


class DetailsFragment : BaseFragment<FragmentDetailsBinding, BaseViewModel>() {
    val args by navArgs<DetailsFragmentArgs>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailsBinding.inflate(inflater, container, false)

    override fun initViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNav()
        initViews()
        onclicks()
    }


    private fun onclicks() {
        binding.tvUrl.setOnClickListener{
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
        val underlinedText = SpannableString(urlText)
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