//package com.example.newsapp.ui.fragments.categories.sports//package com.example.newsapp.ui.fragments.categories.sports
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.viewModels
//import com.example.domain.models.news.Article
//import com.example.newsapp.base.BaseFragment
//import com.example.newsapp.databinding.FragmentSportsBinding
//import com.example.newsapp.ui.adapters.NewsAdapter
//import com.example.newsapp.ui.fragments.NewsViewModel
//import com.example.newsapp.utils.SPORT_CATEGORY
//import com.google.android.material.tabs.TabLayout
//import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class SportsFragment : BaseFragment<FragmentSportsBinding, NewsViewModel>() {
//    private val sportViewModel by viewModels<NewsViewModel>()
//    private val adapter = NewsAdapter()
//
//    override fun initViewModel(): NewsViewModel {
//        return sportViewModel
//    }
//
//    override fun inflateBinding(
//        inflater: LayoutInflater,
//        container: ViewGroup?
//    ): FragmentSportsBinding {
//        return FragmentSportsBinding.inflate(inflater, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel.getNewsSources(SPORT_CATEGORY)
//        initViews()
//        observeLiveData()
//    }
//
//    private fun observeLiveData() {
//        viewModel.sourcesLiveData.observe(viewLifecycleOwner) { sourcesList ->
//            showNewsSources(sourcesList)
//        }
//        viewModel.newsLiveData.observe(viewLifecycleOwner) { newsList ->
//            showNewsList(newsList)
//        }
//
//    }
//
//    private fun initViews() {
//        binding.recyclerView.adapter = adapter
//    }
//
//    private fun showNewsSources(sources: List<com.example.data.api.models.sources.SourcesItem?>?) {
//        sources?.forEach { source ->
//            val tab = binding.tabLayout.newTab()
//            tab.setText(source?.name)
//            tab.tag = source
//            binding.tabLayout.addTab(tab)
//        }
//        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                val source = tab?.tag as com.example.data.api.models.sources.SourcesItem
//                viewModel.getNews(source.id ?: "")
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//                val source = tab?.tag as com.example.data.api.models.sources.SourcesItem
//                viewModel.getNews(source.id ?: "")
//            }
//        })
//
//    }
//
//
//    private fun showNewsList(articles:List<Article?>?) {
//        adapter.changeNewsList(articles)
//    }
//
//
//}
