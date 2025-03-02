package com.example.mohitekacareassignment.presentation.core

import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
object SavedScreen

@Serializable
data class WebViewScreen(
    val article: String,
    val isSaved: Boolean
)