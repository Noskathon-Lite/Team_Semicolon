package com.example.surakhsit_nepal.MainPages.Sections

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.surakhsit_nepal.Components.BelowNavBar
import com.example.surakhsit_nepal.Components.TopNavBar
import com.example.surakhsit_nepal.ui.theme.backgroundColor



@Composable
fun EmergencyNumber(navController : NavHostController){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(){
            TopNavBar()
            BelowNavBar("Emergency Contacts"){
                navController.popBackStack()
            }
            Column(
                modifier = Modifier.padding(top = 10.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ColumnsBox("Nepal Police","100")
                ColumnsBox("Fire Support","101")
                ColumnsBox("Ambulance Support","102")
                ColumnsBox("Traffice Support","103")
                ColumnsBox("Armed Police Support","1114")
                ColumnsBox("Child Helpline","104")
                ColumnsBox("Women Helpline","1145")
            }



        }


    }

}


@Composable
fun ColumnsBox(
    name : String,
    contact : String,
){
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxWidth(.9f)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .height(60.dp),
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(end = 10.dp,top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ){
            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 20.dp)
            ) {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Text(
                    text ="Contact : $contact",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
            Button(
                onClick = {
                    val phoneNUmber = contact

                    val u = Uri.parse("tel:$phoneNUmber")
                    val i  = Intent(Intent.ACTION_DIAL,u)
                    try{
                        context.startActivity(i)
                    }catch (e :Exception){
                        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                        Log.e("failure","${e.message}")

                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color.White),


                ){
                Text(
                    text = " Call",
                    color = backgroundColor,

                    )

            }

        }

    }
}



