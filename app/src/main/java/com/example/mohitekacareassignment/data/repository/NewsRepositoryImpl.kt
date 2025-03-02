package com.example.mohitekacareassignment.data.repository

import com.example.mohitekacareassignment.data.model.toDomainArticle
import com.example.mohitekacareassignment.data.remote.NewsAPI
import com.example.mohitekacareassignment.domain.model.Article
import com.example.mohitekacareassignment.domain.repository.NewsRepository
import com.example.mohitekacareassignment.utils.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsAPI: NewsAPI
): NewsRepository {

    override suspend fun getHomeNews(): Response<List<Article>> {
        return try {
            val response = newsAPI.getHomeNews()
            if (response.isSuccessful) {
                Response.Success(response.body()?.articles?.map { it.toDomainArticle() })
            } else {
                Response.Error(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            Response.Error(e.message)
        }
    }
}