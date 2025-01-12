package com.example.surakhsit_nepal.Navigation

import android.annotation.SuppressLint
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.surakhsit_nepal.DataStore.DataStoreManager
import com.example.surakhsit_nepal.MainPages.MainScreen
import com.example.surakhsit_nepal.OnBoardingScreens.OnBoardingScreen
import com.example.surakhsit_nepal.UserVerification.Login
import com.example.surakhsit_nepal.UserVerification.Registration

@SuppressLint("NewApi")
@Composable
fun SetUpNavigation(navController: NavHostController) {
    val context = LocalContext.current
    val dataStoreManager = DataStoreManager(context)
    val checkStatus by dataStoreManager.getStatus.collectAsState(initial = null)

    if (checkStatus == null) {
        LoadingScreen()
    } else {
        val startDestination = if(checkStatus == true) Screens.mainScreen.route else Screens.registration.route


        NavHost(
            navController = navController,
            startDestination = startDestination
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
            composable(Screens.mainScreen.route) {
                MainScreen(navController)
            }

        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()

    }
}