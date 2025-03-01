package com.example.mohitekacareassignment.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    var isSaved: Boolean = false
)