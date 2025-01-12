package com.example.surakhsit_nepal.MainPages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.surakshitnepal.Components.BottomBarSection
import com.example.surakshitnepal.Components.BottomSectionType

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainScreen(navController : NavHostController) {
    // State to track which section is selected
    var selectedSection by remember { mutableStateOf(BottomSectionType.Home) }

    Scaffold(
        bottomBar = {
            BottomBarSection(
                selectionSection = selectedSection ,
                onSelectedSection = { selectedSection = it }
            )
        },
        content = { paddingValues ->
            when (selectedSection) {
                BottomSectionType.Home -> HomePage(navController)
                BottomSectionType.Profile -> ProfilePage(navController)
                BottomSectionType.camera -> CameraPage(navController)
            }
        }
    )
}