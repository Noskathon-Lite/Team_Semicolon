package com.example.surakhsit_nepal.Navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.surakhsit_nepal.DataStore.DataStoreManager
import com.example.surakhsit_nepal.MainPages.MainScreen
import com.example.surakhsit_nepal.MainPages.Sections.EmergencyNumber
import com.example.surakhsit_nepal.MainPages.Sections.FeedBack
import com.example.surakhsit_nepal.MainPages.Sections.Notices
import com.example.surakhsit_nepal.MainPages.Sections.WantedList
import com.example.surakhsit_nepal.MainPages.Sections.policeNearBy
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
            composable(route = Screens.OnBoarding.route) {
                OnBoardingScreen(navController)

            }

            composable(route = Screens.registration.route) {
                Registration(navController)

            }

            composable(route = Screens.login.route) {
                Login(navController)
            }
            composable(route = Screens.mainScreen.route) {
                MainScreen(navController)
            }
            composable(Screens.emergency_number.route) {
                EmergencyNumber(navController)
            }
            composable(Screens.feedback.route) {
                FeedBack(navController)
            }
            composable(Screens.notices.route) {
                Notices(navController)
            }
            composable(Screens.policeNearby.route) {
                policeNearBy(navController)
            }
            composable(Screens.wanted.route) {
                WantedList(navController)
            }
        }

    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()

    }
}