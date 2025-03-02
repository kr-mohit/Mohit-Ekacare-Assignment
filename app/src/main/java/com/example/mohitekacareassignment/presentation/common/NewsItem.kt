package com.example.mohitekacareassignment.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.mohitekacareassignment.domain.model.Article

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsItem(
    newsArticle: Article,
    onReadMoreClick: (Article) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = CardDefaults.elevatedShape
            )
    ) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxSize()
        ) {
            val (image, saveIcon, heading, description, readMore) = createRefs()

            GlideImage(
                model = newsArticle.urlToImage,
                contentDescription = "News Thumbnail",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = modifier
                    .background(Color(0xFFFFE895))
                    .aspectRatio(2F)
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
            )
            if (newsArticle.isSaved) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Save",
                    tint = Color.Yellow,
                    modifier = modifier
                        .padding(10.dp)
                        .constrainAs(saveIcon) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            width = Dimension.wrapContent
                            height = Dimension.wrapContent
                        }
                )
            }
            Text(
                text = newsArticle.title,
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                    .constrainAs(heading) {
                        top.linkTo(image.bottom)
                        start.linkTo(image.start)
                        end.linkTo(image.end)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
            )
            Text(
                text = newsArticle.description,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                    .constrainAs(description) {
                        start.linkTo(heading.start)
                        end.linkTo(heading.end)
                        top.linkTo(heading.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
            )
            Text(
                text = "Read more...",
                style = MaterialTheme.typography.labelMedium,
                modifier = modifier
                    .padding(10.dp)
                    .constrainAs(readMore) {
                        end.linkTo(parent.end)
                        top.linkTo(description.bottom)
                        width = Dimension.wrapContent
                        height = Dimension.wrapContent
                    }
                    .clickable { onReadMoreClick(newsArticle) }
            )
        }
    }
}