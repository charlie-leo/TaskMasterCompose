package com.task.master.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.task.master.presentation.common.ThemePrimaryButton
import com.task.master.presentation.ui.viewmodel.MainActivityViewModel
import kotlinx.serialization.Serializable

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

@Serializable
data class FirstClassNavigation(
    val id : Int = 0
){}

@Serializable
data class SecondClassNavigation(
    val id : Int = 0
){}

@Composable
fun Onboarding(mainViewModel: MainActivityViewModel, type: String) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigations.HOME_SCREEN
    )
    {
//        composable(Navigations.SPLASH_SCREEN) {
//            SplashScreen(
//                navController,
//                viewModel = mainViewModel
//            )
//        }
        composable(Navigations.HOME_SCREEN) {
//            HomeScreen(
//                navController = navController,
//                viewModel = mainViewModel,
//                type
//            )

            BottomNavigationScreen()

        }
        composable(Navigations.LOGIN_SCREEN) {
            LoginScreen(
                navController = navController,
                viewModel = mainViewModel
            )
        }


        composable<FirstClassNavigation> {
            val data = it.toRoute<FirstClassNavigation>()

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Text(text = data.id.toString())
                ThemePrimaryButton(text = "First Screen", onClick = {
                    navController.navigate(
                        SecondClassNavigation(id = 1)
                    )
                })
            }
        }

        composable<SecondClassNavigation> {

            val data = it.toRoute<SecondClassNavigation>()

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Text(text = data.id.toString())
                ThemePrimaryButton(text = "Second Screen", onClick = {
                    navController.navigate(
                        FirstClassNavigation(id = 3)
                    )
                })
            }

        }

    }
}


@Composable
fun LoginScreen(navController: NavController, viewModel: MainActivityViewModel) {
    ConstraintLayout() {

    }
}