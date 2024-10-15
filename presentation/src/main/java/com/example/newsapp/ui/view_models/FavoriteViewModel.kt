package com.example.newsapp.ui.view_models

import androidx.lifecycle.viewModelScope
import com.example.domain.models.news.Article
import com.example.domain.use_cases.AddFavoriteArticleUseCase
import com.example.domain.use_cases.DeleteFavoriteArticleUseCase
import com.example.domain.use_cases.GetAllFavoriteArticlesUseCase
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
   private val deleteFavoriteArticleUseCase: DeleteFavoriteArticleUseCase
): BaseViewModel() {

   private val _articles = MutableStateFlow<List<Article>>(emptyList())
   val articles = _articles.asStateFlow()

    fun getFavoriteArticles(){
      viewModelScope.launch{
         getAllFavoriteArticlesUseCase.invoke().collect{articlesList->
            _articles.emit(articlesList)
         }
      }
   }
   fun addArticle(article: Article) {
      viewModelScope.launch {
         addFavoriteArticleUseCase.invoke(article)
      }
   }

   fun deleteArticle(article: Article) {
      viewModelScope.launch {
         deleteFavoriteArticleUseCase.invoke(article)
      }
   }


}