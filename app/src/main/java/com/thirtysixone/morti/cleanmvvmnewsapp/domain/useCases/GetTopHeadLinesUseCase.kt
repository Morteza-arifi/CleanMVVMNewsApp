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
 * @author Morteza Arifi on 12/26/22.
 */
class GetTopHeadLinesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke() : Flow<Resource<List<Article>>> = flow {

        emit(Resource.Loading())

        try {
            val articleDTOs = newsRepository.getTopHeadLines().articles
            emit(Resource.Success(articleDTOs.map{it.toArticle()}))
        } catch (e: HttpException){
            emit(Resource.Error(errorMessage = e.localizedMessage ?: "Unknown Http Connection Error"))
        } catch (e : IOException) {
            emit(Resource.Error(errorMessage = e.localizedMessage ?: "Server is not available"))
        }

    }

}