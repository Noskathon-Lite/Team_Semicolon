package com.example.surakhsit_nepal.UserVerification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.surakhsit_nepal.Navigation.Screens
import com.example.surakhsit_nepal.ui.theme.backgroundColor




@Composable
fun Login(navController: NavHostController){
    var username by remember{ mutableStateOf("") }
    var email by remember{ mutableStateOf("") }
    var phone_number by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(backgroundColor),
        contentAlignment  = Alignment.CenterStart
    ){

        Column(
            modifier = Modifier.padding(top= 80.dp)
        ){
            Text(
                text = "Signup",
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp)

            )
            Text(
                text = "Signup to get access to all the feature!!",
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 20.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Box(
                modifier = Modifier.fillMaxSize()
                    .shadow(elevation = 10.dp)
                    .clip(shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(Color.White),

                ){

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(140.dp))

                    OutlinedTextField(
                        value = phone_number,
                        onValueChange = {phone_number = it},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = { Text(text = "Phone Number") },
                        shape = RoundedCornerShape(10.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "",
                                tint = backgroundColor
                            )
                        }
                    )


                    OutlinedTextField(
                        value = password,
                        onValueChange = {password = it},
                        label = { Text(text = "password") },
                        shape = RoundedCornerShape(10.dp),
                        visualTransformation = if(isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "",
                                tint = backgroundColor
                            )
                        },
                        trailingIcon = {

                            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                Icon(
                                    imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = if (isPasswordVisible) "Hide password" else "show password"
                                )

                            }

                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth(.8f),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor)

                    ) {
                        Text(text = " Signup", fontSize = 18.sp)
                    }
                    Row(
                        modifier = Modifier.padding(bottom = 90.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(text = "New User?", fontSize = 16.sp)
                        Text(
                            text = "Signup",
                            color = backgroundColor,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Screens.registration.route)
                                }
                        )

                    }


                }

            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun showData(){
    val navController = rememberNavController()
    Login(navController)
}