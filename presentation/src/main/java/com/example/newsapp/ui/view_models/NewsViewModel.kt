package com.example.newsapp.ui.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.headlines.Source
import com.example.domain.models.news.Article
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
    private val getSourcesUseCase: GetSourcesUseCase
) : BaseViewModel() {

    val sourcesLiveData = MutableLiveData<Result<List<Source>?>>()
    val newsLiveData = MutableLiveData<List<Article>?>()


    fun getHeadLines(category: String) {
        showLoading(messageId = R.string.loading)
        viewModelScope.launch {
            try {
                val response = getHeaLinesUseCase.invoke(category)
                hideLoading()
                newsLiveData.value = response
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