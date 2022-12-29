package com.thirtysixone.morti.cleanmvvmnewsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thirtysixone.morti.cleanmvvmnewsapp.data.local.dao.NewsArticleDao
import com.thirtysixone.morti.cleanmvvmnewsapp.data.local.entity.ArticleEntity

/**
 * @author Morteza Arifi on 12/27/22.
 */
@Database(entities = [ArticleEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class NewsArticleDatabase : RoomDatabase() {

    abstract fun getNewsArticleDao() : NewsArticleDao

    companion object{
        const val DATABASE_NAME = "news_db"
    }

}