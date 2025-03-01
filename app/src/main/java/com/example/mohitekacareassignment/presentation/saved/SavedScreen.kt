package com.example.mohitekacareassignment.presentation.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SavedScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
            .background(Color(0xFFA9FFAA)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Saved",
            color = Color.Black
        )
    }
}