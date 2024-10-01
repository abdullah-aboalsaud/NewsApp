package com.example.newsapp.ui.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentCategoriesBinding
import com.example.newsapp.ui.models.CategoryModel
import com.example.newsapp.utils.BUSINESS_CATEGORY
import com.example.newsapp.utils.ENTERTAINMENT_CATEGORY
import com.example.newsapp.utils.GENERAL_CATEGORY
import com.example.newsapp.utils.HEALTH_CATEGORY
import com.example.newsapp.utils.SCIENCE_CATEGORY
import com.example.newsapp.utils.SPORT_CATEGORY
import com.example.newsapp.utils.TECHNOLOGY_CATEGORY
import com.example.newsapp.utils.hideBottomNav


class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    val categoryAdapter = CategoriesAdapter()
    private var categoriesList = mutableListOf<CategoryModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAdapterList()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpAdapterWithClick()
    }

    override fun onStart() {
        super.onStart()
        hideBottomNav()
    }

    private fun setUpAdapterWithClick() {
        binding.recyclerCategory.adapter = categoryAdapter
        categoryAdapter.onItemClick = CategoriesAdapter.OnItemClick { categoryTitle ->
            findNavController().navigate(
                CategoriesFragmentDirections.actionCategoriesFragmentToHomeFragment(
                    categoryTitle
                )
            )
        }

    }

    private fun setAdapterList() {
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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}