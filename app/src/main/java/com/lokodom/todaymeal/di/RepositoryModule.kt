package com.lokodom.todaymeal.di

import com.lokodom.todaymeal.repository.MealRepository
import com.lokodom.todaymeal.repository.RepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: RepositoryImp): MealRepository

}