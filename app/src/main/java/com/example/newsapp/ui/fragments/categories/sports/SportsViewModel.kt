package com.example.newsapp.ui.fragments.categories.sports

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.R
import com.example.newsapp.api.NewsServices
import com.example.newsapp.api.model.news.ArticlesItem
import com.example.newsapp.api.model.news.NewsResponse
import com.example.newsapp.api.model.sources.SourcesItem
import com.example.newsapp.api.model.sources.SourcesResponse
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.base.UiMessage
import com.example.newsapp.utils.SPORT_CATEGORY
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(
    private val newsServices: NewsServices
) : BaseViewModel() {
    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    val newsLiveData = MutableLiveData<List<ArticlesItem?>?>()


    fun getNewsSources() {
        uiMessage.value = UiMessage(showLoading = true)
        newsServices.getNewsSources(category = SPORT_CATEGORY)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    p0: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    hideLoading()
                    if (response.isSuccessful) {
                        sourcesLiveData.value = response.body()?.sources
                    } else {
                        handelError(response)
                    }
                }

                override fun onFailure(p0: Call<SourcesResponse>, error: Throwable) {
                    Log.e("onFailure", error.localizedMessage ?: "no error")
                    handelError(error) {
                        getNewsSources()
                    }
                    uiMessage.value = UiMessage(
                        showLoading = false,
                        exception = error,
                        posBtnId = R.string.retry,
                        onPositiveClick = {
                            getNewsSources()
                        }
                    )


                }

            })

    }

    fun getNews(sourceId: String) {
        showLoading(messageId = R.string.loading)
        newsServices.getNews(sourceId = sourceId)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(p0: Call<NewsResponse>, response: Response<NewsResponse>) {
                    hideLoading()
                    if (response.isSuccessful) {
                        newsLiveData.value = response.body()?.articles
                    } else {

                        handelError(response)
                    }
                }

                override fun onFailure(p0: Call<NewsResponse>, error: Throwable) {
                    hideLoading()
                    handelError(error)
                    Log.e("getNews-onFailure->", error.localizedMessage ?: "something went wrong")
                }
            })
    }

}