package com.thirtysixone.morti.cleanmvvmnewsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Article
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Source

/**
 * @author Morteza Arifi on 12/27/22.
 */
@Entity
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val source: Source?,
    val title: String? = "",
    val url: String = "",
    val urlToImage: String? = ""
) {
    fun toArticle() = Article(
        id = id,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}
