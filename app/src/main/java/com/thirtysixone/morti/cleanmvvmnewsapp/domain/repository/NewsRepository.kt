package com.thirtysixone.morti.cleanmvvmnewsapp.domain.repository

import androidx.lifecycle.LiveData
import com.thirtysixone.morti.cleanmvvmnewsapp.data.local.entity.ArticleEntity
import com.thirtysixone.morti.cleanmvvmnewsapp.data.remote.dto.NewsResponseDto
import kotlinx.coroutines.flow.Flow

/**
 * @author Morteza Arifi on 12/26/22.
 */
interface NewsRepository {

    suspend fun getTopHeadLines() : NewsResponseDto

    suspend fun searchNews(search: String) : NewsResponseDto

    suspend fun saveOrUpdateNewsArticle(article: ArticleEntity)

    fun getAllSavedNewsArticles() : Flow<List<ArticleEntity>>

    suspend fun findArticle(url: String, title: String, publishedAt: String): ArticleEntity?

    suspend fun deleteNewsArticle(article: ArticleEntity)
}