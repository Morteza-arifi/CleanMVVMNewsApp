package com.thirtysixone.morti.cleanmvvmnewsapp.presentation.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.thirtysixone.morti.cleanmvvmnewsapp.R
import com.thirtysixone.morti.cleanmvvmnewsapp.databinding.NewsArticleItemBinding
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Article

/**
 * @author Morteza Arifi on 12/26/22.
 */
class NewsAdapter : ListAdapter<Article, NewsAdapter.NewsViewHolder>(ArticleDiffUtil()) {

    class ArticleDiffUtil() : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    class NewsViewHolder(val binding: NewsArticleItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            NewsArticleItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        holder.binding.apply {
            title.text = article.title
            description.text = article.description
            publishedAt.text = article.publishedAt
            source.text = article.source?.name
            Glide.with(this.root)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(image)

            root.setOnClickListener(){
                onItemClickListener?.let {
                    it(article)
                }
            }

        }
    }

    private var onItemClickListener : ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}