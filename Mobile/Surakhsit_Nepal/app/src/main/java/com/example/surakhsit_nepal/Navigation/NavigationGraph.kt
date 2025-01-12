package com.example.surakhsit_nepal.Navigation

import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.surakhsit_nepal.OnBoardingScreens.OnBoardingScreen
import com.example.surakhsit_nepal.UserVerification.Login
import com.example.surakhsit_nepal.UserVerification.Registration

@Composable
fun SetUpNavigation(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = Screens.onBoardingScreen.route
    ) {
        composable(Screens.onBoardingScreen.route) {
            OnBoardingScreen(navController)
        }
        composable(Screens.registration.route) {
            Registration(navController)
        }
        composable(Screens.login.route) {
            Login(navController)
        }

    }
}