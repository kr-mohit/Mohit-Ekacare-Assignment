package com.example.mohitekacareassignment.domain.repository

import com.example.mohitekacareassignment.domain.model.Article
import com.example.mohitekacareassignment.utils.Response

interface NewsRepository {

    suspend fun getAllNews(): Response<List<Article>>
}