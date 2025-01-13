package com.example.surakhsit_nepal.Navigation

sealed class Screens(val route : String){
    object registration : Screens(route = "Signup")
    object OnBoarding : Screens(route = "Onboarding")
    object mainScreen : Screens(route = "MainScreen")
    object emergency_number : Screens(route = "Emergency Number")
    object feedback : Screens(route = "Feedback")
    object notices : Screens(route = "Notices")
    object policeNearby : Screens(route = "PoliceStation")
    object wanted : Screens(route = "WantedList")
    object login : Screens(route = "Login")
    object nearByPolice : Screens(route = "NearByPolice")

}