package com.thirtysixone.morti.cleanmvvmnewsapp.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.thirtysixone.morti.cleanmvvmnewsapp.data.local.Converters
import com.thirtysixone.morti.cleanmvvmnewsapp.data.local.NewsArticleDatabase
import com.thirtysixone.morti.cleanmvvmnewsapp.data.local.dao.NewsArticleDao
import com.thirtysixone.morti.cleanmvvmnewsapp.data.remote.api.NewsApi
import com.thirtysixone.morti.cleanmvvmnewsapp.data.utils.Constants.Companion.BASE_URL
import com.thirtysixone.morti.cleanmvvmnewsapp.data.utils.GsonParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author Morteza Arifi on 12/26/22.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)


    @Provides
    @Singleton
    fun provideNewsArticleDatabase(@ApplicationContext context: Context): NewsArticleDatabase =
        Room.databaseBuilder(
            context,
            NewsArticleDatabase::class.java,
            NewsArticleDatabase.DATABASE_NAME
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()

    @Provides
    @Singleton
    fun provideNewsArticleDao(newsArticleDatabase: NewsArticleDatabase): NewsArticleDao =
        newsArticleDatabase.getNewsArticleDao()
}