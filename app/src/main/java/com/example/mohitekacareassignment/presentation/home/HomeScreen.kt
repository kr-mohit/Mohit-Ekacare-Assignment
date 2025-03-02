package com.example.mohitekacareassignment.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.mohitekacareassignment.domain.model.Article
import com.example.mohitekacareassignment.presentation.core.NewsViewModel
import com.example.mohitekacareassignment.presentation.core.WebViewScreen
import com.example.mohitekacareassignment.utils.Response

@Composable
fun HomeScreen(navController: NavHostController, viewModel: NewsViewModel, modifier: Modifier = Modifier) {

    val response = viewModel.articles.observeAsState()

    when(response.value) {
        is Response.Error -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = response.value?.error ?: "Something Went Wrong")
            }
        }
        is Response.Loading -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }
        is Response.Success -> {
            if (response.value == null) {
                Column(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Empty Response from server")
                }
                return
            }
            val articles = response.value!!.data
            if (articles.isNullOrEmpty()) {
                Column(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "No news found")
                }
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
                        .padding(10.dp),
                ) {
                    items(articles) { article ->
                        NewsItem(
                            newsArticle = article,
                            navController = navController
                        )
                    }
                }
            }
        }
        null -> {}
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsItem(
    newsArticle: Article,
    navController: NavHostController,
    modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(3F),
        onClick = {
            navController.navigate(WebViewScreen(newsArticle.url, newsArticle.isSaved))
        }
    ) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxSize()
        ) {
            val (image, heading, description, readMore) = createRefs()

            GlideImage(
                model = newsArticle.urlToImage,
                contentDescription = "News Thumbnail",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = modifier
                    .background(Color(0xFFFFE895))
                    .aspectRatio(1F)
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    }
            )
            Text(
                text = newsArticle.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .padding(10.dp)
                    .constrainAs(heading) {
                        top.linkTo(image.top)
                        start.linkTo(image.end)
                        width = Dimension.fillToConstraints
                    }
            )
            Text(
                text = newsArticle.description,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .padding(10.dp, 0.dp)
                    .constrainAs(description) {
                        start.linkTo(image.end)
                        end.linkTo(parent.end)
                        top.linkTo(heading.bottom)
                        bottom.linkTo(readMore.top)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
            )
            Text(
                text = "Read more...",
                style = MaterialTheme.typography.labelMedium,
                modifier = modifier
                    .padding(10.dp)
                    .constrainAs(readMore) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.wrapContent
                    }
            )
        }
    }
}