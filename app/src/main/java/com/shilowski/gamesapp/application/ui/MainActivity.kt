package com.shilowski.gamesapp.application.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.shilowski.gamesapp.common.domain.navigation.Screen
import com.shilowski.gamesapp.common.ui.TestScreen
import com.shilowski.gamesapp.common.ui.theme.GamesAppTheme
import com.shilowski.gamesapp.home.ui.navigation.homeNavigationModule
import com.shilowski.gamesapp.room.ui.navigation.roomNavigationModule

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GamesAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    Navigation(navController)
//                    TestScreen()
                }
            }
        }
    }
}

@Composable
private fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        homeNavigationModule(navController)
        roomNavigationModule(navController)
    }
}