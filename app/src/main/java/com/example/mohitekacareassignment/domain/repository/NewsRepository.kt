package com.example.mohitekacareassignment.domain.repository

import com.example.mohitekacareassignment.domain.model.Article
import com.example.mohitekacareassignment.utils.Response

interface NewsRepository {

    suspend fun getHomeNews(): Response<List<Article>>

    suspend fun getAllSavedNews(): Response<List<Article>>

    suspend fun saveArticle(article: Article): Boolean

    suspend fun deleteArticle(article: Article): Boolean
}