package com.thirtysixone.morti.cleanmvvmnewsapp.domain.useCases

import com.thirtysixone.morti.cleanmvvmnewsapp.common.Resource
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Article
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * @author Morteza Arifi on 12/29/22.
 */
class SearchNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(search: String) : Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
            val articles = newsRepository.searchNews(search).articles.map { it.toArticle() }
            emit(Resource.Success(articles))
        } catch (e: HttpException){
            emit(Resource.Error(errorMessage = e.localizedMessage ?: "Unknown Http Error"))
        } catch (e: IOException){
            emit(Resource.Error(errorMessage = "Server is not Available"))
        }
    }
}