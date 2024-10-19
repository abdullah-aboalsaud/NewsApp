package com.example.newsapp.ui.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.news.Article
import com.example.domain.use_cases.AddFavoriteArticleUseCase
import com.example.domain.use_cases.DeleteFavoriteArticleUseCase
import com.example.domain.use_cases.GetAllFavoriteArticlesUseCase
import com.example.domain.use_cases.IsArticleInFavoritesUseCase
import com.example.newsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllFavoriteArticlesUseCase: GetAllFavoriteArticlesUseCase,
    private val addFavoriteArticleUseCase: AddFavoriteArticleUseCase,
    private val deleteFavoriteArticleUseCase: DeleteFavoriteArticleUseCase,
    private val isArticleInFavoritesUseCase: IsArticleInFavoritesUseCase
) : BaseViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles = _articles.asStateFlow()

    private val _isFavoriteAdded = MutableLiveData<Boolean>()
    val isFavoriteAdded = _isFavoriteAdded

    fun checkIfArticleInFavorites(url: String) {
        viewModelScope.launch {
            val isInFavorites = isArticleInFavoritesUseCase.invoke(url)
            _isFavoriteAdded.postValue(isInFavorites)
        }
    }

    fun getFavoriteArticles() {
        viewModelScope.launch {
            getAllFavoriteArticlesUseCase.invoke().collect { articlesList ->
                _articles.emit(articlesList)
            }
        }
    }

    fun addArticle(article: Article) {
        viewModelScope.launch {
            try {
                addFavoriteArticleUseCase.invoke(article)
                _isFavoriteAdded.postValue(true)
            } catch (ex: Exception) {
                _isFavoriteAdded.postValue(false)
            }

        }
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            deleteFavoriteArticleUseCase.invoke(article)
        }
    }


}