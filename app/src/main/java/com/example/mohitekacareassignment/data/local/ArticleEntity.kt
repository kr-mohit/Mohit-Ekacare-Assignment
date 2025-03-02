package com.example.mohitekacareassignment.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mohitekacareassignment.domain.model.Article

@Entity
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)

fun ArticleEntity.toDomainArticle() = Article(
    author, content, description, publishedAt, title, url, urlToImage, isSaved = true
)