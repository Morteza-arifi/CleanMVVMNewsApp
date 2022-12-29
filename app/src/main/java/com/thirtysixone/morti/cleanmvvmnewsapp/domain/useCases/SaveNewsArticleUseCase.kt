package com.thirtysixone.morti.cleanmvvmnewsapp.domain.useCases

import com.thirtysixone.morti.cleanmvvmnewsapp.data.local.entity.ArticleEntity
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Article
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.toArticleEntity
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.repository.NewsRepository
import javax.inject.Inject

/**
 * @author Morteza Arifi on 12/27/22.
 */
class SaveNewsArticleUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article): Boolean {
        val articleEntity :ArticleEntity? =
            newsRepository.findArticle(article.url, article.title!!, article.publishedAt!!)
        try {
            if (articleEntity?.id == null) {
                newsRepository.saveOrUpdateNewsArticle(article.toArticleEntity())
            }
        } catch (e: java.lang.Exception) {
            return false
        }
        return true
    }
}