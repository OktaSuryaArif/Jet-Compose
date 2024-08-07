package edu.osa.osaapps

import ProfileScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.osa.osaapps.navigation.Screen
import edu.osa.osaapps.ui.screen.detail.DetailScreen
import edu.osa.osaapps.ui.screen.home.HomeScreen
import edu.osa.osaapps.ui.theme.SubmissionTheme

// Class For OSA
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OSATravelApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "OSATravel App") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Profile.route){
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Account")
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { travelId ->
                        navController.navigate(Screen.DetailTravel.createRoute(travelId))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.DetailTravel.route,
                arguments = listOf(navArgument("travelId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("travelId") ?: -1L
                DetailScreen(
                    travelId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToCart = {
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OSAHeroesAppPreview() {
    SubmissionTheme {
        OSATravelApp()
    }
}

