package com.example.newsapp.ui.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.headlines.Source
import com.example.domain.models.news.Article
import com.example.domain.use_cases.GetArticlesBySourceIdUseCase
import com.example.domain.use_cases.GetHeadLinesUseCase
import com.example.domain.use_cases.GetSourcesUseCase
import com.example.domain.utils.Result
import com.example.newsapp.R
import com.example.newsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getHeaLinesUseCase: GetHeadLinesUseCase,
    private val getSourcesUseCase: GetSourcesUseCase,
    private val getArticlesBySourceIdUseCase: GetArticlesBySourceIdUseCase
) : BaseViewModel() {

    val sourcesLiveData = MutableLiveData<Result<List<Source>?>>()
    val headLinesLiveData = MutableLiveData< Result<List<Article>?>>()
    val articlesBySourceIdLiveData = MutableLiveData< Result<List<Article>?>>()



    fun getHeadLines(category: String) {
        showLoading(messageId = R.string.loading)
        viewModelScope.launch {
            try {
                val response = getHeaLinesUseCase.invoke(category)
                hideLoading()
                headLinesLiveData.value = Result.Success(response)
            } catch (ex: Exception) {
                handelError(ex)
            }
        }

    }


    fun getNewsSources(category: String) {
        sourcesLiveData.value = Result.Loading()

        viewModelScope.launch() {
            try {
                val response = getSourcesUseCase.invoke(category = category)
                sourcesLiveData.value = Result.Success(response)

            } catch (ex: Exception) {
               // handelError(ex)
                sourcesLiveData.value = Result.Error(ex.localizedMessage?:"")
            }

        }
    }


    fun getArticlesBySourceId(sourceId: String) {
        articlesBySourceIdLiveData.value = Result.Loading()
        viewModelScope.launch {
            try {
                val response = getArticlesBySourceIdUseCase.invoke(sourceId)
                articlesBySourceIdLiveData.value = Result.Success(response)
            } catch (ex: Exception) {
               // handelError(ex)
                articlesBySourceIdLiveData.value= Result.Error(ex.localizedMessage?:"")
            }
        }

    }

}