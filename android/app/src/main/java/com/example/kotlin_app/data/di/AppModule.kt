package com.example.kotlin_app.data.di

import android.app.Application
import androidx.room.Room
import com.example.kotlin_app.data.api.ReposApi
import com.example.kotlin_app.data.data.ReposDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ReposApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideReposApi(retrofit: Retrofit): ReposApi =
        retrofit.create(ReposApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : ReposDatabase =
        Room.databaseBuilder(app, ReposDatabase::class.java, "repos_database")
            .fallbackToDestructiveMigration()
            .build()
}