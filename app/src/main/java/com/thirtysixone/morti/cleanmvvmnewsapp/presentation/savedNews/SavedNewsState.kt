package com.thirtysixone.morti.cleanmvvmnewsapp.presentation.savedNews

import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Article

/**
 * @author Morteza Arifi on 12/28/22.
 */

sealed class SavedNewsPageState {

    data class SavedNewsListState(
        val isLoading: Boolean = false,
        val articles: List<Article> = emptyList(),
    ) : SavedNewsPageState()

    data class SavedNewsActionState(
        val articleDeleted: Boolean = false,
        val articleDeletedUndo: Boolean = false
    ) : SavedNewsPageState()
}
