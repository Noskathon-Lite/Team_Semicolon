package com.example.surakhsit_nepal.MainPages


import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.ContactEmergency
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.NotificationsOff
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.persistableBundleOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.surakhsit_nepal.Components.TopNavBar
import com.example.surakhsit_nepal.Navigation.Screens
import com.example.surakhsit_nepal.ui.theme.backgroundColor
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomePage(navController : NavHostController) {

    val context = LocalContext.current

    //permission ko lagi notification
    val PermissionState = rememberPermissionState(
        android.Manifest.permission.POST_NOTIFICATIONS
    )

    var count by remember { mutableIntStateOf(0) }
    var isNotificationClicked  = if(count%2 ==0){
        true
    }else{
        false
    }

    val hour = LocalTime.now().hour
    val greetingText = when (hour) {
        in 1..12 -> "Good Morning"
        in 12..16 -> "Good Afternoon"
        else -> "Good Evening"
    }
    Column() {
        TopNavBar()
        Scaffold(


        ) { _ ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.padding(top = 30.dp, start = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "${greetingText},\n Kiran Acharya")
                        Spacer(modifier = Modifier.width(200.dp))

                        Image(
                            imageVector =
                            if(isNotificationClicked) Icons.Default.NotificationsActive else Icons.Default.NotificationsOff,
                            contentDescription = "notification icon",
                            colorFilter = ColorFilter.tint(backgroundColor),
                            modifier = Modifier.clickable {
                                isNotificationClicked = true
                                count++

                            }
                        )
                    }
                    Text(
                        "Welcome to Surakshit Nepal App.",
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(30.dp),
                        modifier = Modifier.padding(start = 20.dp, top = 30.dp)
                    ) {
                        IconsImage(Icons.Default.LocationOn, "police Stations\n Nearby"){
                            navController.navigate(Screens.policeNearby.route)
                        }
                        IconsImage(Icons.Default.Book, "Notices"){
                            navController.navigate(Screens.notices.route)
                        }
                        IconsImage(Icons.Default.Feedback, "Feedback"){
                            navController.navigate(Screens.feedback.route)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(30.dp),
                        modifier = Modifier.padding(start = 20.dp, top = 30.dp)
                    ) {
                        IconsImage(Icons.Default.ContactEmergency, "Emergency\n Numbers"){
                            navController.navigate(Screens.emergency_number.route)
                        }
                        IconsImage(Icons.Default.Inbox, "e-Complaint"){
                            Toast.makeText(context,"This section will be availble soon!!", Toast.LENGTH_LONG).show()}
                        IconsImage(Icons.Default.PersonPin," Wanted "){
                            navController.navigate(Screens.wanted.route)
                        }
                    }
                }
            }

        }
        LaunchedEffect(isNotificationClicked) {
            PermissionState.launchPermissionRequest()
        }
    }
}



@Composable
fun IconsImage(image : ImageVector,text : String,onClick : ()-> Unit){
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
                onClick()
            }

    ){
        Image(
            imageVector = image,
            contentDescription = "",
            colorFilter = ColorFilter.tint(backgroundColor),
            modifier = Modifier.size(50.dp)
        )
        Text(
            text = text,
            fontSize = 16.sp,
            //color = backgroundColor,

        )
    }

}

@Preview(showBackground = true)
@Composable
fun showBackground(){
    val navController = rememberNavController()
    HomePage(navController)
}