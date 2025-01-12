package com.example.surakhsit_nepal.MainPages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.surakhsit_nepal.Components.BelowNavBar
import com.example.surakhsit_nepal.Components.TopNavBar
import com.example.surakhsit_nepal.Navigation.Screens
import com.example.surakhsit_nepal.ui.theme.backgroundColor

@Composable
fun ProfilePage(navController : NavHostController){
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopNavBar()
            BelowNavBar("Profile Section") {
                //navController.navigate(Screens.mainScreen.route)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier.fillMaxWidth(.8f)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(backgroundColor),
                contentAlignment = Alignment.CenterStart


            ){
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp,top = 10.dp)
                ){
                    Text(
                        "Personal Information",
                        color = Color.White,
                        fontSize = 22.sp
                    )
                    Text(
                        "Name : Kiran Acharya",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                    Text(
                        "Email : Kiran.211720@ncit.edu.np",
                        color = Color.White,
                        fontSize = 16.sp
                    )

                    Text(
                        "Phone : 9865445343",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }


            }

        }

    }

}
@Preview(showBackground = true)
@Composable
fun ShowProfile() {

    val navController = rememberNavController()
    ProfilePage(navController)
}
