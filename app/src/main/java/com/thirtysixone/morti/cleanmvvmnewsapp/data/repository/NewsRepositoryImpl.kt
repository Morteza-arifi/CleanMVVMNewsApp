package com.thirtysixone.morti.cleanmvvmnewsapp.data.repository

import androidx.lifecycle.LiveData
import com.thirtysixone.morti.cleanmvvmnewsapp.data.local.dao.NewsArticleDao
import com.thirtysixone.morti.cleanmvvmnewsapp.data.local.entity.ArticleEntity
import com.thirtysixone.morti.cleanmvvmnewsapp.data.remote.api.NewsApi
import com.thirtysixone.morti.cleanmvvmnewsapp.data.remote.dto.NewsResponseDto
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Morteza Arifi on 12/26/22.
 */
class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsArticleDao: NewsArticleDao
) : NewsRepository {
    override suspend fun getTopHeadLines(): NewsResponseDto {
        return newsApi.getTopHeadlines()
    }

    override suspend fun searchNews(search: String): NewsResponseDto {
        return newsApi.searchNews(search)
    }

    override suspend fun saveOrUpdateNewsArticle(article: ArticleEntity) {
        return newsArticleDao.saveOrUpdateNewsArticle(article)
    }

    override fun getAllSavedNewsArticles(): Flow<List<ArticleEntity>> {
        return newsArticleDao.getAllSavedNewsArticles()
    }

    override suspend fun findArticle(url: String, title: String, publishedAt: String): ArticleEntity {
        return newsArticleDao.findArticle(url,title,publishedAt)
    }

    override suspend fun deleteNewsArticle(article: ArticleEntity) {
        return newsArticleDao.deleteNewsArticle(article)
    }


}