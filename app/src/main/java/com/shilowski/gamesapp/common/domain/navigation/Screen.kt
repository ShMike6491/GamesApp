package com.shilowski.gamesapp.common.domain.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home_route")
    object SelectGame : Screen("select_game_route")
    object Room : Screen("room_route")
}