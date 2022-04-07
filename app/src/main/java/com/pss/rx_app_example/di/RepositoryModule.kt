package com.pss.rx_app_example.di

import com.pss.rx_app_example.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

/*    @Provides
    @Singleton
    fun provideMainRepository(

    ) = MainRepository()*/
}