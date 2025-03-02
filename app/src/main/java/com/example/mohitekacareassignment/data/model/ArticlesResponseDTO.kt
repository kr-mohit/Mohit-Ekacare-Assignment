package com.example.mohitekacareassignment.data.model

data class ArticlesResponseDTO(
    val articles: List<ArticleDTO>?,
    val status: String?,
    val totalResults: Int?,
    val message: String?
)