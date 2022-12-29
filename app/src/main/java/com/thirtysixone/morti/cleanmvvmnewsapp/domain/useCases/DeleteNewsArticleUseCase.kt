package com.thirtysixone.morti.cleanmvvmnewsapp.domain.useCases

import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Article
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.toArticleEntity
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.repository.NewsRepository
import javax.inject.Inject

/**
 * @author Morteza Arifi on 12/27/22.
 */
class DeleteNewsArticleUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        newsRepository.deleteNewsArticle(article.toArticleEntity())
    }
}