package com.thirtysixone.morti.cleanmvvmnewsapp.data.remote.api

import com.thirtysixone.morti.cleanmvvmnewsapp.data.remote.dto.NewsResponseDto
import com.thirtysixone.morti.cleanmvvmnewsapp.data.utils.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Morteza Arifi on 12/26/22.
 */
interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("pageSize") pageSize: Int = 30,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY,
    ): NewsResponseDto

    @GET("/v2/everything")
    suspend fun searchNews(
        @Query("q") search: String,
        @Query("pageSize") pageSize: Int = 30,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY,
    ): NewsResponseDto
}