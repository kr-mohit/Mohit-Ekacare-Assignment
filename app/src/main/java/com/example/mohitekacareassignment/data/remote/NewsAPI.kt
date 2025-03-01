package com.example.mohitekacareassignment.data.remote

import com.example.mohitekacareassignment.data.model.ArticlesResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface NewsAPI {

    // 0eb511e13b5041cd9b99f41cf8007892

    @GET("everything")
    suspend fun getAllNews(
    ): Response<ArticlesResponseDTO>
}