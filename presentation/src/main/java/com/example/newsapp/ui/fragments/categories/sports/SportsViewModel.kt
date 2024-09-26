package com.example.newsapp.ui.fragments.categories.sports

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.api.NewsServices
import com.example.newsapp.R
import com.example.newsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(
    private val newsServices: NewsServices
) : BaseViewModel() {
    val sourcesLiveData = MutableLiveData<List<com.example.data.api.model.sources.SourcesItem?>?>()
    val newsLiveData = MutableLiveData<List<com.example.data.api.model.news.ArticlesItem?>?>()


    fun getNewsSources(category: String) {
        showLoading(messageId = R.string.loading)
        viewModelScope.launch() {
            try {
                val response = newsServices.getNewsSources(category = category)
                hideLoading()
                sourcesLiveData.value = response.sources
            } catch (ex: Exception) {
                handelError(ex)
            }

        }
    }


    fun getNews(sourceId: String) {
        showLoading(messageId = R.string.loading)
        viewModelScope.launch {
            try {
                val response = newsServices.getNews(sourceId = sourceId)
                hideLoading()
                newsLiveData.value = response.articles
            } catch (ex: Exception) {
                handelError(ex)
            }
        }

    }

}