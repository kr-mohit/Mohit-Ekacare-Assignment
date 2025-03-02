package com.example.mohitekacareassignment.presentation.core.webview

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.mohitekacareassignment.presentation.core.NewsViewModel

@Composable
fun WebViewScreen(article: String, isSaved: Boolean, viewModel: NewsViewModel) {
    ConstraintLayout {

        val (ctaSave) = createRefs()

        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()
                    loadUrl(article)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
        Button(
            onClick = { viewModel.onSaveClick(article) },
            modifier = Modifier.padding(10.dp)
                .clip(shape = CircleShape)
                .background(Color.Transparent)
                .padding(20.dp)
                .constrainAs(ctaSave) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent
                }
        ) {
            Icon(
                imageVector = if (isSaved) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = "Save",
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}