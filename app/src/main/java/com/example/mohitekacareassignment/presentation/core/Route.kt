package com.example.mohitekacareassignment.presentation.core

import com.example.mohitekacareassignment.domain.model.Article
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
object SavedRoute

@Serializable
data class WebViewRoute(
    val article: Article
)