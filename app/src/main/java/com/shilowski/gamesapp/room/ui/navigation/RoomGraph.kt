package com.shilowski.gamesapp.room.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shilowski.gamesapp.common.domain.navigation.Screen
import com.shilowski.gamesapp.room.ui.RoomScreen

// todo: if user exits room or navigates back, the app should take him to the main page!!
fun NavGraphBuilder.roomNavigationModule(navController: NavController) {
    roomLink(navController)
}

fun NavGraphBuilder.roomLink(navController: NavController) {
    composable(route = Screen.Room.route) {
        // todo: add view model and pass the state
        RoomScreen(navController = navController)
    }
}