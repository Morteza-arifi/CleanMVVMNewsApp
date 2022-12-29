package com.thirtysixone.morti.cleanmvvmnewsapp.di

import com.thirtysixone.morti.cleanmvvmnewsapp.data.repository.NewsRepositoryImpl
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Morteza Arifi on 12/26/22.
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindNewsRepository(impl: NewsRepositoryImpl) : NewsRepository
}