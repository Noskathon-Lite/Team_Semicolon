package com.example.surakhsit_nepal.MainPages.Sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.surakhsit_nepal.Components.BelowNavBar
import com.example.surakhsit_nepal.Components.TopNavBar
import com.example.surakhsit_nepal.ui.theme.backgroundColor



@Composable
fun FeedBack(navController : NavHostController){
    var feedbackText by remember { mutableStateOf("") }
    Column() {
        TopNavBar()
        BelowNavBar("FeedBack Section"){
            navController.popBackStack()
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(top =20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = feedbackText,
                    onValueChange = { feedbackText = it },
                    label = { Text("Your Feedback") },
                    placeholder = { Text("Your feedback here...") },
                    modifier = Modifier.fillMaxWidth(.8f)
                        .height(200.dp),
                    colors = TextFieldDefaults.colors(backgroundColor)
                )

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(.6f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor)
                ) {
                    Text(text ="Submit", fontSize = 16.sp)
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun showFeedback(){
    val navController = rememberNavController()
    FeedBack(navController)

}