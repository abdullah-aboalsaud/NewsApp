package com.example.newsapp.ui.fragments.categories.all_categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.databinding.FragmentCategoriesBinding


class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}