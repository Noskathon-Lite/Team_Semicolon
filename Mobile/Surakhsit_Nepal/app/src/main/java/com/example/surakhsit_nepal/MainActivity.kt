package com.example.surakhsit_nepal

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.surakhsit_nepal.MainPages.CameraPage
import com.example.surakhsit_nepal.MainPages.HomePage
import com.example.surakhsit_nepal.MainPages.MainScreen
import com.example.surakhsit_nepal.MainPages.Sections.FeedBack
import com.example.surakhsit_nepal.Navigation.SetUpNavigation
import com.example.surakhsit_nepal.OnBoardingScreens.Indicator
import com.example.surakhsit_nepal.OnBoardingScreens.OnBoardingScreen
import com.example.surakhsit_nepal.UserVerification.Registration
import com.example.surakhsit_nepal.ui.theme.Surakhsit_NepalTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navController = rememberNavController()
            //OnBoardingScreen()
           // SetUpNavigation(navController)
            //HomePage(navController)
           // MainScreen(navController)
            //CameraPage(navController)
            FeedBack(navController)


        }
    }
}

