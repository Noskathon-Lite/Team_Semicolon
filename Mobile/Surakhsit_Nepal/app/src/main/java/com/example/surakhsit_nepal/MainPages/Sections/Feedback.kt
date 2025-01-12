package com.example.surakhsit_nepal.MainPages.Sections

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.surakhsit_nepal.Backend.BackendData.BackendViewModel
import com.example.surakhsit_nepal.Components.BelowNavBar
import com.example.surakhsit_nepal.Components.TopNavBar
import com.example.surakhsit_nepal.feedbackTest.FeedbackRequest
import com.example.surakhsit_nepal.ui.theme.backgroundColor
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun FeedBack(navController : NavHostController,viewModel: BackendViewModel = viewModel()){
    var feedbackText by remember { mutableStateOf("") }
    var feedbackTitle by remember { mutableStateOf("") }
    var feedbackResult by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column() {
        TopNavBar()
        BelowNavBar("FeedBack Section"){
            navController.popBackStack()
        }
        Box(
            modifier = Modifier.fillMaxSize(),
           // contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(top =20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = feedbackTitle,
                    onValueChange = { feedbackTitle  = it },
                    label = { Text("Title") },
                    placeholder = { Text("") },
                    modifier = Modifier.fillMaxWidth(.8f)
                        .height(60.dp),
                    colors = TextFieldDefaults.colors(backgroundColor),

                )

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
                    onClick = {

                        val feedbackRequest = FeedbackRequest(
                            title = feedbackTitle,
                            content = feedbackText
                        )
                        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzY4MjA4OTg1LCJpYXQiOjE3MzY2NzI5ODUsImp0aSI6IjY0MThmN2M2ZGZhYjQ1NWNhZGI3YjEzNGJhZDU1MjE1IiwidXNlcl9pZCI6Mn0._dTs6iszpk7sergxY4e-QsX9cIevCq0AZkf68l0RsLg"
                        viewModel.sendFeedback(token,feedbackRequest){result ->
                            feedbackResult = result

                            Toast.makeText(context,"Feedback sent Successfully",Toast.LENGTH_SHORT).show()

                        }



                    },
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