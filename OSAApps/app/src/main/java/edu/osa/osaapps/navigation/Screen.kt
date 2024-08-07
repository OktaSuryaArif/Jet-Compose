package edu.osa.osaapps.navigation

// Class For Screen
sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Profile: Screen("profile")
    object DetailTravel : Screen("home/{travelId}") {
        fun createRoute(travelId: Long) = "home/$travelId"
    }
}