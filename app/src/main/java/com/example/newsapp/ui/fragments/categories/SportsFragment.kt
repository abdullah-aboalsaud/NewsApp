package com.example.newsapp.ui.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentSportsBinding


class SportsFragment : BaseFragment<FragmentSportsBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSportsBinding {
        return FragmentSportsBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}