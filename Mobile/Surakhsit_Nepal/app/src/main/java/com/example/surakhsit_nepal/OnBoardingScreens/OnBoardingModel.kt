package com.example.surakhsit_nepal.OnBoardingScreens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.vector.ImageVector


sealed class OnBoardingModel(
    val title: String,
    val description: String,
    val icon : ImageVector? = null
){
    object Onboarding1 : OnBoardingModel(
        title = " Send Video",
        description =
        " Capture and send a video of the incident directly to the authorities. " +
                "This feature allows you to provide real-time visual evidence of a crime or suspicious activity, " +
                "helping law enforcement respond quickly and effectively. Share crucial footage to support investigations and enhance safety in your community.",
        icon = Icons.Default.Add

    )

    object Onboarding2 : OnBoardingModel(
        title = "Report Crime",
        description =
        "Report a crime or emergency with just a few taps. You can send details like the exact crime location, " +
                "type of crime, and additional information to help authorities act swiftly. This feature ensures that your report reaches the right people, " +
                "enabling them to respond faster and more efficiently.",
        icon = Icons.Default.Add

    )

    object Onboarding3 : OnBoardingModel(
        title = "Navigation",
        description =
        " In case of an emergency or to report a crime in person, " +
                "this feature helps you quickly find the nearest police station and provides directions to get there." +
                " Ensuring you never feel lost in an urgent situation, this tool helps save time and potentially enhances the safety of your community.",
        icon = Icons.Default.Add

    )
}