package com.example.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: ArticleEntityDM)

    @Delete
    suspend fun deleteArticle(article: ArticleEntityDM)

    @Query("select * from ArticleEntityDM")
    fun getAllFavoriteArticles(): Flow<List<ArticleEntityDM>>

    @Query("SELECT EXISTS(SELECT 1 FROM ArticleEntityDM WHERE url = :url LIMIT 1)")
    suspend fun isArticleInFavorites(url: String): Boolean

}