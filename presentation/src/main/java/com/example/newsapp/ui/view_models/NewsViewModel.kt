package com.example.newsapp.ui.view_models

import android.util.Printer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.headlines.Source
import com.example.domain.models.news.Article
import com.example.domain.use_cases.GetArticlesBySourceIdUseCase
import com.example.domain.use_cases.GetHeadLinesUseCase
import com.example.domain.use_cases.GetSourcesUseCase
import com.example.domain.use_cases.SearchForNewsUseCase
import com.example.domain.utils.Resource
import com.example.newsapp.R
import com.example.newsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getHeaLinesUseCase: GetHeadLinesUseCase,
    private val getSourcesUseCase: GetSourcesUseCase,
    private val getArticlesBySourceIdUseCase: GetArticlesBySourceIdUseCase,
    private val searchForNewsUseCase: SearchForNewsUseCase
) : BaseViewModel() {

    val sourcesLiveData = MutableLiveData<List<Source>?>()
    val headLinesLiveData = MutableLiveData<List<Article>?>()
    val articlesBySourceIdLiveData = MutableLiveData<List<Article>?>()
    val searchLiveData = MutableLiveData<List<Article>?>()
    val isLoadingArticles = MutableLiveData<Boolean>()


    fun getHeadLines(category: String) {
        showLoading()
        viewModelScope.launch {
            val response = getHeaLinesUseCase.invoke(category)
            when (response) {
                is Resource.Failure -> handelError(response.throwable)
                is Resource.Success -> headLinesLiveData.value = response.data
            }
        }

    }


    fun getNewsSources(category: String) {
        showLoading()
        viewModelScope.launch() {
            val response = getSourcesUseCase.invoke(category = category)
            when (response) {
                is Resource.Failure -> handelError(response.throwable)
                is Resource.Success -> sourcesLiveData.value = response.data
            }
        }
    }

    fun getArticlesBySourceId(sourceId: String) {
        isLoadingArticles.value = true
        viewModelScope.launch {
            val response = getArticlesBySourceIdUseCase.invoke(sourceId)
            when (response) {
                is Resource.Failure -> {
                    isLoadingArticles.value = false
                    handelError(response.throwable)
                }
                is Resource.Success -> {
                    isLoadingArticles.value = false
                    articlesBySourceIdLiveData.postValue(response.data)
                }
            }

        }
    }

    fun searchForNews(searchQuery: String) {
        isLoadingArticles.value = true
        viewModelScope.launch {
            val response = searchForNewsUseCase.invoke(searchQuery)
            when (response) {
                is Resource.Failure -> {
                    isLoadingArticles.value = false
                    handelError(response.throwable)
                }

                is Resource.Success -> {
                    isLoadingArticles.value = false
                    searchLiveData.value = response.data
                }
            }

        }
    }



}