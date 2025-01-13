package com.example.surakhsit_nepal

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.osmandroid.OSM.GeoLiveLocation.LocationViewModel
import com.example.osmandroid.OSM.MapCompose
import com.example.surakhsit_nepal.MainPages.CameraPage
import com.example.surakhsit_nepal.MainPages.HomePage
import com.example.surakhsit_nepal.MainPages.MainScreen
import com.example.surakhsit_nepal.MainPages.Sections.FeedBack
import com.example.surakhsit_nepal.MainPages.Sections.WantedList
import com.example.surakhsit_nepal.Navigation.SetUpNavigation
import com.example.surakhsit_nepal.OnBoardingScreens.Indicator
import com.example.surakhsit_nepal.OnBoardingScreens.OnBoardingScreen
import com.example.surakhsit_nepal.UserVerification.Registration
import com.example.surakhsit_nepal.ui.theme.Surakhsit_NepalTheme

import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    val locationViewModel: LocationViewModel by viewModels()
    lateinit var navController: NavHostController
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaunchedEffect(isActive) {
                if(isActive){
                    while (true) {
                        locationViewModel.fetchCurrentLocation()
                        delay(2500)
                    }
                }

            }
            navController = rememberNavController()
            //OnBoardingScreen()
           //SetUpNavigation(navController,locationViewModel)
            //HomePage(navController)
           // MainScreen(navController)
            //CameraPage(navController)
           // FeedBack(navController)
        //    WantedList(navController)

          //  CameraPage(navController)

            MapCompose(locationViewModel)


        }
    }
}

