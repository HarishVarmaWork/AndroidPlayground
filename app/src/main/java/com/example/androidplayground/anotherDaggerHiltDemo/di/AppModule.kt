package com.example.androidplayground.anotherDaggerHiltDemo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val baseURL = "https://api.github.com/search/"  //repositories?q=newyork


    @Singleton
    @Provides
    fun getRetroRepository(retrofit: Retrofit): RetroRepository {
        return retrofit.create(RetroRepository::class.java);
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val request: Request = chain.request()
            chain.proceed(request)
        }.build()
    }

}