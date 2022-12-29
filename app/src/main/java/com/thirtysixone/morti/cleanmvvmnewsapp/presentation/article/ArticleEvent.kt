package com.thirtysixone.morti.cleanmvvmnewsapp.presentation.article

import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Article

/**
 * @author Morteza Arifi on 12/27/22.
 */
sealed class ArticleEvent(
    val article: Article
) {
    class SaveArticle(article: Article) : ArticleEvent(article)
}
