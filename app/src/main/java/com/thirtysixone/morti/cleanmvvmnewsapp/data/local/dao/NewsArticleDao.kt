package com.thirtysixone.morti.cleanmvvmnewsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.thirtysixone.morti.cleanmvvmnewsapp.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author Morteza Arifi on 12/27/22.
 */
@Dao
interface NewsArticleDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveOrUpdateNewsArticle(article: ArticleEntity)

    @Query("select * from ArticleEntity")
    fun getAllSavedNewsArticles(): Flow<List<ArticleEntity>>

    @Query("select * from ArticleEntity where url is :url and title is :title and publishedAt is :publishedAt")
    suspend fun findArticle(url: String, title: String, publishedAt: String): ArticleEntity

    @Delete
    suspend fun deleteNewsArticle(article: ArticleEntity)
}