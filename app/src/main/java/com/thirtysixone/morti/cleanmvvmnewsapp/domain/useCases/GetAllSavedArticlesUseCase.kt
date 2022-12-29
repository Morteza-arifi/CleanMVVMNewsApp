package com.thirtysixone.morti.cleanmvvmnewsapp.domain.useCases

import androidx.lifecycle.LiveData
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Article
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author Morteza Arifi on 12/27/22.
 */
class GetAllSavedArticlesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke() = newsRepository.getAllSavedNewsArticles().map {
        it.map {  articleEntity ->
            articleEntity.toArticle()
        }
    }
}




