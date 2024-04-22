package com.task.master.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.task.master.presentation.ui.activities.SplashScreen
import com.task.master.presentation.ui.viewmodel.MainActivityViewModel

/**
 * Created by Charles Raj I on 14/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */

object Navigations {
    var SPLASH_SCREEN = "splashScreen"
    var LOGIN_SCREEN = "loginScreen"
    var SIGNUP_SCREEN = "signupScreen"
    var HOME_SCREEN = "homeScreen"
}

@Composable
fun Onboarding(mainViewModel: MainActivityViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigations.SPLASH_SCREEN
    )
    {
        composable(Navigations.SPLASH_SCREEN) {
            SplashScreen(
                navController,
                viewModel = mainViewModel
            )
        }
        composable(Navigations.HOME_SCREEN) {
            HomeScreen(
                navController = navController,
                viewModel = mainViewModel
            )
        }
        composable(Navigations.LOGIN_SCREEN) {
            LoginScreen(
                navController = navController,
                viewModel = mainViewModel
            )
        }
        // Add more destinations as needed
    }
}


@Composable
fun LoginScreen(navController: NavController, viewModel: MainActivityViewModel) {
    ConstraintLayout() {

    }
}