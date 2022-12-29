package com.thirtysixone.morti.cleanmvvmnewsapp.presentation.topHeadLineNews

import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Article

/**
 * @author Morteza Arifi on 12/26/22.
 */
data class TopHeadLinesState(
    val isLoading : Boolean = false,
    val data : List<Article> = emptyList(),
    val errorMessage : String = ""
)
