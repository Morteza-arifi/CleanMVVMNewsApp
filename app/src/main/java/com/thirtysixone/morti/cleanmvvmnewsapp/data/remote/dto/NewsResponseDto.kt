package com.thirtysixone.morti.cleanmvvmnewsapp.data.remote.dto

data class NewsResponseDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)