package com.example.dagger_hiltwithapi.di

import com.example.dagger_hiltwithapi.Network.PostApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
   /* @Binds
    @Singleton
    fun getPost():PostApiService*/
    @Provides
    fun providesBaseUrl():String = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun providesRetrofitBuilder(baseUrl : String) : Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providesPostApiService(retrofit: Retrofit) : PostApiService =
        retrofit.create(PostApiService::class.java)
}