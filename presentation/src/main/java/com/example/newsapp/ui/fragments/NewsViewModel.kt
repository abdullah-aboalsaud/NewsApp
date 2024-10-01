package com.example.newsapp.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.example.domain.models.news.Article
import com.example.domain.use_cases.GetHeadLinesUseCase
import com.example.newsapp.R
import com.example.newsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getSourcesUseCase: GetHeadLinesUseCase
) : BaseViewModel() {

    //  val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    val newsLiveData = MutableLiveData<List<Article>?>()


    fun getHeadLines(language: String) {
        showLoading(messageId = R.string.loading)
        viewModelScope.launch {
            try {
                val response = getSourcesUseCase.invoke(language)
                hideLoading()
                newsLiveData.value = response
            } catch (ex: Exception) {
                handelError(ex)
            }
        }

    }


//    fun getNewsSources(category: String) {
//        showLoading(messageId = R.string.loading)
//        viewModelScope.launch() {
//            try {
//                val response = webServices.getNewsSources(category = category)
//                hideLoading()
//                sourcesLiveData.value = response.sources
//            } catch (ex: Exception) {
//                handelError(ex)
//            }
//
//        }
//    }


//    fun getNewsBySourceId(sourceId: String) {
//        showLoading(messageId = R.string.loading)
//        viewModelScope.launch {
//            try {
//                val response = webServices.getNewsBySourceId(sourceId = sourceId)
//                hideLoading()
//                newsLiveData.value = response.articles
//            } catch (ex: Exception) {
//                handelError(ex)
//            }
//        }
//
//    }

}