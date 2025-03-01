package com.example.mohitekacareassignment.presentation.core

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.util.fastForEachIndexed
import com.example.mohitekacareassignment.domain.model.Article
import com.example.mohitekacareassignment.presentation.home.HomeScreen
import com.example.mohitekacareassignment.presentation.saved.SavedScreen

@Composable
fun MainScreen(tempArticles: List<Article>, modifier: Modifier = Modifier) {

    val navBarItems = listOf(
        NavBarItem("Home", Icons.Default.Home),
        NavBarItem("Saved", Icons.Default.Star)
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                navBarItems.fastForEachIndexed { index, navBarItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            Icon(imageVector = navBarItem.icon, contentDescription = navBarItem.name)
                        },
                        label = {
                            Text(text = navBarItem.name)
                        }
                    )
                }
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        ContentScreen(tempArticles, modifier = Modifier.padding(innerPadding), selectedIndex)
    }
}

@Composable
fun ContentScreen(tempArticles: List<Article>, modifier: Modifier = Modifier, selectedIndex: Int = 0) {
    when(selectedIndex) {
        0 -> HomeScreen(tempArticles, modifier)
        1 -> SavedScreen(modifier)
    }
}

data class NavBarItem(
    val name: String,
    val icon: ImageVector
)