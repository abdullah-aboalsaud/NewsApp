package com.example.newsapp.ui.fragments.categories.all_categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentCategoriesBinding
import com.example.newsapp.utils.BUSINESS_CATEGORY
import com.example.newsapp.utils.ENTERTAINMENT_CATEGORY
import com.example.newsapp.utils.GENERAL_CATEGORY
import com.example.newsapp.utils.HEALTH_CATEGORY
import com.example.newsapp.utils.SCIENCE_CATEGORY
import com.example.newsapp.utils.SPORT_CATEGORY
import com.example.newsapp.utils.TECHNOLOGY_CATEGORY


class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    val categoryAdapter = CategoriesAdapter()
    private var categoriesList = mutableListOf<ModelCategory>()

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

    private fun setUpAdapterWithClick() {
        binding.recyclerCategory.adapter = categoryAdapter
        categoryAdapter.onItemClick = CategoriesAdapter.OnItemClick { title ->
            if (title == SPORT_CATEGORY) {
                findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToSprotsFragment())
            }
        }

    }

    private fun setAdapterList() {
        categoriesList.add(
            ModelCategory(SPORT_CATEGORY, R.color.blue)
        )
        categoriesList.add(
            ModelCategory(TECHNOLOGY_CATEGORY, R.color.blue)
        )
        categoriesList.add(
            ModelCategory(SCIENCE_CATEGORY, R.color.blue)
        )
        categoriesList.add(
            ModelCategory(HEALTH_CATEGORY, R.color.blue)
        )
        categoriesList.add(
            ModelCategory(GENERAL_CATEGORY, R.color.blue)
        )
        categoriesList.add(
            ModelCategory(ENTERTAINMENT_CATEGORY, R.color.blue)
        )
        categoriesList.add(
            ModelCategory(BUSINESS_CATEGORY, R.color.blue)
        )
        categoryAdapter.submitList(categoriesList)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}