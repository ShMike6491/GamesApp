package com.shilowski.gamesapp.home.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shilowski.gamesapp.common.domain.navigation.Screen
import com.shilowski.gamesapp.home.ui.HomeScreen
import com.shilowski.gamesapp.home.ui.SelectGameScreen

// todo: possibly use custom back stack?
fun NavGraphBuilder.homeNavigationModule(navController: NavController) {
    homeLink(navController)
    selectGameLink(navController)
}

fun NavGraphBuilder.homeLink(navController: NavController) {
    composable(route = Screen.Home.route) {
        // todo: add view model and pass the state
        HomeScreen(navController = navController)
    }
}

fun NavGraphBuilder.selectGameLink(navController: NavController) {
    composable(route = Screen.SelectGame.route) {
        // todo: add view model and pass the state
        SelectGameScreen(navController = navController)
    }
}