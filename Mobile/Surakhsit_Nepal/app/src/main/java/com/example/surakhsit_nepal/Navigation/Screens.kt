package com.example.surakhsit_nepal.Navigation

sealed class Screens(val route : String){
    object  registration : Screens("Registration")
    object login : Screens("Login")
    object onBoardingScreen : Screens("OnBoardingScreens")

}