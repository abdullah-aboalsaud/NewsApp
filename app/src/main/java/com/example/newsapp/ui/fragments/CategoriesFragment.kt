package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentCategoriesBinding


class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCategoriesBinding {
        return FragmentCategoriesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onClicks()
    }

    private fun onClicks() {
        binding.ivSports.setOnClickListener {

            findNavController()
                .navigate(CategoriesFragmentDirections.actionCategoriesFragmentToSprotsFragment())
        }
    }


}