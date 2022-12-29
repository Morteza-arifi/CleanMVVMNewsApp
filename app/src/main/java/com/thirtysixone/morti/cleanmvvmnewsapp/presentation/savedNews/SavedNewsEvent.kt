package com.thirtysixone.morti.cleanmvvmnewsapp.presentation.savedNews

import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Article

/**
 * @author Morteza Arifi on 12/28/22.
 */
sealed class SavedNewsEvent {
    data class DeleteArticle(val article: Article) : SavedNewsEvent()
    data class UndoDeleteArticle(val article: Article) : SavedNewsEvent()
}