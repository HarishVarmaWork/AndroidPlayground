package com.example.androidplayground.anotherDaggerHiltDemo.di

import com.example.androidplayground.anotherDaggerHiltDemo.model.DisplayList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroRepository {

    @GET("repositories")
    suspend fun getDataFromAPI(@Query("q") searchString: String): Response<DisplayList>/*Call<DisplayList>*/

}