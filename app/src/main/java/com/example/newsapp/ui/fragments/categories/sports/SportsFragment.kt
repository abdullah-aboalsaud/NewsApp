package com.example.newsapp.ui.fragments.categories.sports

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.newsapp.R
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.news.ArticlesItem
import com.example.newsapp.api.model.news.NewsResponse
import com.example.newsapp.api.model.sources.SourcesItem
import com.example.newsapp.api.model.sources.SourcesResponse
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentSportsBinding
import com.example.newsapp.ui.adapters.NewsAdapter
import com.example.newsapp.utils.SPORT_CATEGORY
import com.example.newsapp.utils.hideLoadingDialog
import com.example.newsapp.utils.showDialog
import com.example.newsapp.utils.showLoadingDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SportsFragment : BaseFragment<FragmentSportsBinding>() {
    private lateinit var progressDialog: ProgressDialog
    val sportViewModel by viewModels<SportsViewModel>()
    val adapter = NewsAdapter()
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSportsBinding {
        return FragmentSportsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNewsSources()
        initViews()
    }

    private fun initViews() {
        binding.recyclerView.adapter = adapter
    }

    private fun getNewsSources() {
        ApiManager.getServices()
            .getNewsSources(category = SPORT_CATEGORY)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    p0: Call<SourcesResponse>, response: Response<SourcesResponse>
                ) {
                    if (response.isSuccessful) {
                        showNewsSources(response.body()?.sources)
                    } else {

                        handelError(response)

                    }
                }

                override fun onFailure(p0: Call<SourcesResponse>, error: Throwable) {
                    Log.e("onFailure", error.localizedMessage ?: "no error")
                    requireContext().showDialog(
                        message = getString(R.string.something_went_wrong),
                        positiveBtnText = getString(R.string.retry),
                        onPositiveClick = {
                            getNewsSources()
                        }
                    )

                }

            })
    }


    private fun showNewsSources(sources: List<SourcesItem?>?) {
        sources?.forEach { source ->
            val tab = binding.tabLayout.newTab()
            tab.setText(source?.name)
            tab.tag = source
            binding.tabLayout.addTab(tab)
        }
        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as SourcesItem
                getNews(source.id ?: "")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as SourcesItem
                getNews(source.id ?: "")
            }

        })

    }


    private fun getNews(sourceId: String) {
        progressDialog = requireContext().showLoadingDialog("Loading...")
        ApiManager.getServices()
            .getNews(sourceId = sourceId)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(p0: Call<NewsResponse>, response: Response<NewsResponse>) {
                    progressDialog.hideLoadingDialog()
                    if (response.isSuccessful) {
                        showNewsList(response.body()?.articles)
                    } else {
                        handelError(response)
                    }
                }

                override fun onFailure(p0: Call<NewsResponse>, error: Throwable) {
                    progressDialog.hideLoadingDialog()
                    Log.e("getNews-onFailure->", error.localizedMessage ?: "something went wrong")
                }
            })
    }


    private fun showNewsList(articles: List<ArticlesItem?>?) {
        adapter.changeNewsList(articles)
    }


}