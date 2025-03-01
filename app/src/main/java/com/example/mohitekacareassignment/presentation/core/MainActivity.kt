package com.example.mohitekacareassignment.presentation.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mohitekacareassignment.domain.model.Article
import com.example.mohitekacareassignment.domain.model.Source
import com.example.mohitekacareassignment.presentation.core.theme.MohitEkaCareAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempArticles = listOf(
            Article(
                title = "This is a news article",
                description = "This is a description of the news article",
                author = "",
                content = "",
                publishedAt = "",
                source = Source("", ""),
                url = "",
                urlToImage = "https://samples-files.com/samples/images/jpg/1920-1080-sample.jpg"
            )
        )
        enableEdgeToEdge()
        setContent {
            MohitEkaCareAssignmentTheme {
                MainScreen(tempArticles)
            }
        }
    }
}