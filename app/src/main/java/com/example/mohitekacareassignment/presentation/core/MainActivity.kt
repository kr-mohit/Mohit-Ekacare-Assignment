package com.example.mohitekacareassignment.presentation.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.example.mohitekacareassignment.domain.model.Source
import com.example.mohitekacareassignment.presentation.core.theme.MohitEkaCareAssignmentTheme
import com.example.mohitekacareassignment.presentation.core.webview.WebViewScreen
import com.example.mohitekacareassignment.presentation.home.HomeScreen
import com.example.mohitekacareassignment.presentation.saved.SavedScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempArticles = listOf(
            Article(
                title = "Watch: Moment India police unplug Ed Sheeran's street performance",
                description = "The star was stopped from busking ahead of his concert in the southern Indian city of Bengaluru.",
                author = "",
                content = "British pop star Ed Sheeran was stopped from busking on a street in the southern Indian city of Bengaluru by local police. The incident happened on Sunday, ahead of his concert in the city. Video t… [+370 chars]",
                publishedAt = "2025-02-10T07:24:47Z",
                source = Source(id = "", name = "BBC News"),
                url = "https://www.bbc.com/news/videos/c4g3ve3ll21o",
                urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/ebd3/live/e547acb0-e77e-11ef-a819-277e390a7a08.jpg"
            ),
            Article(
                title = "India v Pakistan: Cricket's ultimate grudge match in the desert",
                description = "Sunday’s Champions Trophy clash in Dubai promises a charged atmosphere with strong fan presence on both sides.",
                author = "",
                content = "Gautam Bhattacharyya A billboard in Lahore features India and Pakistan's captains ahead of the Champions Trophy. The last time India and Pakistan clashed in a major ICC 50-over contest was in 2023,… [+5656 chars]",
                publishedAt = "2025-02-22T01:04:20Z",
                source = Source(id = "", name = "BBC News"),
                url = "https://www.bbc.com/news/articles/cly3llmg140o",
                urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/87e6/live/769ea470-f021-11ef-a319-fb4e7360c4ec.jpg"
            ),
            Article(
                title = "Kohli smashes brilliant four to reach century and secure victory for India against Pakistan",
                description = "Virat Kohli \"hammers\" in a four to reach a century and secure India's win against Pakistan in their ICC Champions Trophy match in Dubai.",
                author = "BBC Sport",
                content = "Virat Kohli \"hammers\" in a four to reach a century and secure India's win against Pakistan in their ICC Champions Trophy match in Dubai. Kohli hits century to inspire India to victory over Pakistan",
                publishedAt = "2025-02-23T16:39:51Z",
                source = Source(id = "", name = "BBC News"),
                url = "https://www.bbc.com/sport/cricket/articles/czdn45mynr7o",
                urlToImage = "https://ichef.bbci.co.uk/ace/branded_sport/1200/cpsprodpb/a7ef/live/38d0be50-f204-11ef-8c03-7dfdbeeb2526.jpg"
            ),
            Article(
                title = "India’s New Navigation Satellite Is Stranded in the Wrong Orbit After Thruster Glitch",
                description = "ISRO is exploring alternative uses for the satellite in its current orbit.",
                author = "Passant Rabie",
                content = "A recently launched satellite is stuck in space after failing to fire its thrusters, preventing it from reaching its operational orbit. The Indian Space Research Organization (ISRO) shared an update… [+2439 chars]",
                publishedAt = "2025-02-03T17:50:01Z",
                source = Source(id = "", name = "Gizmodo.com"),
                url = "https://gizmodo.com/indias-new-navigation-satellite-is-stranded-in-the-wrong-orbit-after-thruster-glitch-2000558279",
                urlToImage = "https://gizmodo.com/app/uploads/2025/02/NVS-02-launch.jpeg"
            ),
            Article(
                title = "British tourist dies trekking with friend in Himalayas",
                description = "The two British men were hiking in \"extremely difficult terrain\" when one reportedly fell.",
                author = "",
                content = "Francesca Gillett The local rescue team said the terrain was \"challenging\" as they stretchered the man down the mountain. A British tourist has died while trekking with a friend in the Himalaya moun… [+2209 chars]",
                publishedAt = "2025-02-20T03:58:26Z",
                source = Source(id = "", name = "BBC News"),
                url = "https://www.bbc.com/news/articles/c62zr4pyn84o",
                urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/9d2d/live/787fae70-ef3a-11ef-a819-277e390a7a08.png"
            ),
            Article(
                title = "iPhone 16e Continues Apple's Transition to Manufacturing in India",
                description = "The iPhone 16e will join the four other iPhone 16 models in being made in India, Apple has revealed.",
                author = "Hartley Charlton",
                content = "The iPhone 16e will join the four other iPhone 16 models in being made in India, Apple has revealed. In a response to a query from India's Economic Times, Apple said that \"the entire ‌iPhone 16‌ lineup, including ‌iPhone 16e‌, is being assembled in Ind…",
                publishedAt = "2025-02-21T17:17:28Z",
                source = Source(id = "", name = "MacRumors"),
                url = "https://www.macrumors.com/2025/02/21/iphone-16e-made-in-india/",
                urlToImage = "https://images.macrumors.com/t/6gGIdo2rMSYsiYCLLDeKXaKJBIQ=/1680x/article-new/2025/02/iPhone-16e-USB-C-Port.jpg"
            )
        )
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MohitEkaCareAssignmentTheme {
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
                                    onClick = {
                                        selectedIndex = index
                                        if (index == 1) {
                                            navController.navigate(SavedScreen)
                                        } else {
                                            navController.navigate(HomeScreen)
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
                    NavHost(navController = navController, startDestination = HomeScreen, modifier = Modifier.padding(innerPadding)) {
                        composable<HomeScreen> {
                            HomeScreen(tempArticles, navController)
                        }
                        composable<SavedScreen> {
                            SavedScreen()
                        }
                        composable<WebViewScreen> {
                            val args = it.toRoute<WebViewScreen>()
                            WebViewScreen(args.articleUrl)
                        }
                    }
                }
            }
        }
    }
}

data class NavBarItem(
    val name: String,
    val icon: ImageVector
)