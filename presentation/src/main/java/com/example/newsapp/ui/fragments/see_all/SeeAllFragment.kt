package com.example.newsapp.ui.fragments.see_all


import android.R.attr.onClick
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.databinding.FragmentSeeAllBinding
import com.example.newsapp.ui.adapters.SeeAllAdapter
import com.example.newsapp.utils.hideBottomNav


class SeeAllFragment : BaseFragment<FragmentSeeAllBinding, BaseViewModel>() {
    val seeAllAdapter = SeeAllAdapter()
    val args by navArgs<SeeAllFragmentArgs>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSeeAllBinding.inflate(inflater, container, false)

    override fun initViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNav()
        setUpAdapterWithClick()
        initViews()
        onClick()
    }

    private fun onClick() {
        binding.ivBtnBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }

    private fun setUpAdapterWithClick() {
        binding.rvNews.adapter = seeAllAdapter
        seeAllAdapter.onItemClick = { article ->
            findNavController()
                .navigate(SeeAllFragmentDirections.actionGlobalToDetailsFragment(article))
        }
    }

    private fun initViews() {
        seeAllAdapter.submitList(args.ArticlesList.articles)

    }


}