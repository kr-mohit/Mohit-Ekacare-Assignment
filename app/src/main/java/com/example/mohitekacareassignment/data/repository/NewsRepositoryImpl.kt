package com.example.mohitekacareassignment.data.repository

import com.example.mohitekacareassignment.data.local.ArticleDatabase
import com.example.mohitekacareassignment.data.local.ArticleEntity
import com.example.mohitekacareassignment.data.local.toDomainArticle
import com.example.mohitekacareassignment.data.model.toDomainArticle
import com.example.mohitekacareassignment.data.remote.NewsAPI
import com.example.mohitekacareassignment.domain.model.Article
import com.example.mohitekacareassignment.domain.repository.NewsRepository
import com.example.mohitekacareassignment.utils.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsAPI: NewsAPI,
    private val articleDatabase: ArticleDatabase
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

    override suspend fun getAllSavedNews(): Response<List<Article>> {
        return try {
            val response = articleDatabase.articleDao().getAllArticles()
            Response.Success(response.map { it.toDomainArticle() })
        } catch (e: Exception) {
            Response.Error(e.message)
        }
    }

    override suspend fun saveArticle(article: Article): Boolean {
        val articleEntity = ArticleEntity(0, article.author, article.content, article.description, article.publishedAt, article.title, article.url, article.urlToImage)
        return try {
            articleDatabase.articleDao().insertArticle(articleEntity)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteArticle(article: Article): Boolean {
        return try {
            articleDatabase.articleDao().deleteArticle(article.url)
            true
        } catch (e: Exception) {
            false
        }
    }
}