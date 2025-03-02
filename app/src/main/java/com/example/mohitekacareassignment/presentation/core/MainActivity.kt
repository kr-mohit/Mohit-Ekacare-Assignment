package com.example.mohitekacareassignment.presentation.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mohitekacareassignment.domain.model.Article
import com.example.mohitekacareassignment.presentation.core.theme.MohitEkaCareAssignmentTheme
import com.example.mohitekacareassignment.presentation.core.webview.WebViewScreen
import com.example.mohitekacareassignment.presentation.home.HomeScreen
import com.example.mohitekacareassignment.presentation.saved.SavedScreen
import com.example.mohitekacareassignment.utils.CustomNavType
import com.example.mohitekacareassignment.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel: NewsViewModel by viewModels()
        if (Utils.isUserOnline(this)) {
            viewModel.getHomeArticles()
        } else {
            viewModel.onNoInternet()
        }
        viewModel.getSavedArticles()

        setContent {
            MohitEkaCareAssignmentTheme {
                MyApp(viewModel)
            }
        }
    }
}

@Composable
fun MyApp(viewModel: NewsViewModel) {
    val navController = rememberNavController()

    val navBarItems = listOf(
        NavBarItem("Home", Icons.Default.Home),
        NavBarItem("Saved", Icons.Default.Star)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                navBarItems.fastForEachIndexed { index, navBarItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            if (index == 1) {
                                navController.navigate(SavedRoute) {
                                    popUpTo(HomeRoute) { inclusive = true }
                                    launchSingleTop = true
                                }
                            } else {
                                navController.navigate(HomeRoute) {
                                    popUpTo(SavedRoute) { inclusive = true }
                                    launchSingleTop = true
                                }
                            }
                        },
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
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = HomeRoute, modifier = Modifier.padding(innerPadding)) {
            composable<HomeRoute> {
                HomeScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
            composable<SavedRoute> {
                SavedScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
            composable<WebViewRoute>(
                typeMap = mapOf(
                    typeOf<Article>() to CustomNavType.ArticleType
                )
            ) {
                val args = it.toRoute<WebViewRoute>()
                WebViewScreen(
                    article = args.article,
                    viewModel = viewModel
                )
            }
        }
    }
}

data class NavBarItem(
    val name: String,
    val icon: ImageVector
)