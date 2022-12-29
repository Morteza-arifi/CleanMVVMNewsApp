package com.thirtysixone.morti.cleanmvvmnewsapp.presentation.searchNews

import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Article

/**
 * @author Morteza Arifi on 12/29/22.
 */
data class SearchNewsState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val errorMessage: String = ""
)
