package com.example.mohitekacareassignment.presentation.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mohitekacareassignment.presentation.common.CenterLoader
import com.example.mohitekacareassignment.presentation.common.CenterText
import com.example.mohitekacareassignment.presentation.common.NewsItem
import com.example.mohitekacareassignment.presentation.core.NewsViewModel
import com.example.mohitekacareassignment.presentation.core.WebViewScreen
import com.example.mohitekacareassignment.utils.Response

@Composable
fun SavedScreen(navController: NavHostController, viewModel: NewsViewModel, modifier: Modifier = Modifier) {

    val response = viewModel.savedArticles.observeAsState()

    when(response.value) {
        is Response.Error -> {
            CenterText(response.value?.error ?: "Something Went Wrong")
        }
        is Response.Loading -> {
            CenterLoader()
        }
        is Response.Success -> {
            if (response.value == null) {
                CenterText(text = "Empty Response from server")
                return
            }
            val savedArticles = response.value!!.data
            if (savedArticles.isNullOrEmpty()) {
                CenterText(text = "No news found")
                return
            }
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(20.dp, 0.dp),
                ) {
                    items(savedArticles) { article ->
                        NewsItem(
                            newsArticle = article,
                            onReadMoreClick = { newsArticle ->
                                navController.navigate(WebViewScreen(newsArticle.url, newsArticle.isSaved))
                            }
                        )
                    }
                }
            }
        }
        null -> {}
    }
}