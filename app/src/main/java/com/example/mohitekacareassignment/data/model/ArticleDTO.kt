package com.example.mohitekacareassignment.data.model

import com.example.mohitekacareassignment.domain.model.Article
import com.example.mohitekacareassignment.domain.model.Source

data class ArticleDTO(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceDTO?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

fun ArticleDTO.toDomainArticle() = Article(
    author = this.author ?: "",
    content = this.content ?: "",
    description = this.description ?: "",
    publishedAt = this.publishedAt ?: "",
    source = this.source?.toDomainSource() ?: Source("", ""),
    title = this.title ?: "",
    url = this.url ?: "",
    urlToImage = this.urlToImage ?: ""
)