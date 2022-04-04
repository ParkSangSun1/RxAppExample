package com.pss.rx_app_example.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

/*    @Provides
    @Singleton
    fun provideFirebaseRTDB() = FirebaseDatabase.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStore() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }*/
}