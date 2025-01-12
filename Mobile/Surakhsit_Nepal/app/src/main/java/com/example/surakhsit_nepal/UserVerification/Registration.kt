package com.example.surakhsit_nepal.UserVerification

import android.app.Activity
import android.app.PendingIntent
import android.content.IntentSender
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.surakhsit_nepal.Backend.BackendData.userData
import com.example.surakhsit_nepal.Backend.BackendObject
import com.example.surakhsit_nepal.Navigation.Screens
import com.example.surakhsit_nepal.ui.theme.backgroundColor
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Registration(navController: NavHostController){
    var username by remember{ mutableStateOf("") }
    var email by remember{ mutableStateOf("") }
    var phone_number by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val phoneNumberHintIntentResultLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartIntentSenderForResult()
        ) { result ->
            try {
                if (result.resultCode == Activity.RESULT_OK) {
                    val phoneNumberHint = Identity.getSignInClient(context)
                        .getPhoneNumberFromIntent(result.data)
                    phone_number = phoneNumberHint ?: "Unable to fetch number"
                } else {
                    Log.e("Phone", "User cancelled the phone number hint flow.")
                }
            } catch (e: Exception) {
                Log.e("Phone", "Phone Number Hint failed: ${e.message}")
            }
        }

    //phone launcher hint ko lagi
    val getPhoneNumberHint = {
        val request: GetPhoneNumberHintIntentRequest =
            GetPhoneNumberHintIntentRequest.builder().build()
        try {
            Identity.getSignInClient(context)
                .getPhoneNumberHintIntent(request)
                .addOnSuccessListener { pendingIntent: PendingIntent ->
                    try {
                        phoneNumberHintIntentResultLauncher.launch(
                            IntentSenderRequest.Builder(pendingIntent).build()
                        )
                    } catch (e: IntentSender.SendIntentException) {
                        Log.e("Phone", "Launching the PendingIntent failed: ${e.message}")
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("Phone", "Phone Number Hint failed: ${e.message}")
                }
        } catch (e: Exception) {
            Log.e("Phone", "Exception while creating phone number hint request: ${e.message}")
        }
    }

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
                    Spacer(modifier = Modifier.height(40.dp))
                    OutlinedTextField(
                        value = username,
                        onValueChange = {username = it},
                        label = { Text(text = "username") },
                        shape = RoundedCornerShape(10.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "emailIcon",
                                tint = backgroundColor
                            )
                        }
                    )

                    Box() {
                        OutlinedTextField(
                            value = phone_number,
                            onValueChange = { phone_number = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(text = "Phone Number") },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier,
                            readOnly = true,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Phone,
                                    contentDescription = "",
                                    tint = backgroundColor
                                )
                            }
                        )
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clickable { getPhoneNumberHint() }
                                .background(Color.Transparent)
                        )
                    }

                    OutlinedTextField(
                        value = email,
                        onValueChange = {email = it},
                        label = { Text(text = "Email") },
                        shape = RoundedCornerShape(10.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
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
                        onClick = {if (username.isBlank() || email.isBlank() || phone_number.isBlank() || password.isBlank() ) {
                            Toast.makeText(context, "Please fill in all fields and agree to the terms.", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                Toast.makeText(context, "Invalid email format.", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            if (password.length < 6) {
                                Toast.makeText(context, "Password must be at least 6 characters long.", Toast.LENGTH_SHORT).show()
                                return@Button
                            }

                            val registrationData = userData(
                                email = email,
                                name = username,
                                password = password,
                                phone_number = phone_number
                            )



                            scope.launch {
                                try {
                                    val response = BackendObject.authService.registerUser(registrationData)
                                    if (response.isSuccessful) {
                                        Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
                                        navController.navigate(Screens.login.route)
                                    } else {
                                        Toast.makeText(context, "Registration failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                                        Log.e("registration", "Registration failed: ${response.message()}")
                                    }
                                } catch (e: Exception) {

                                    Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                                    Log.e("registration", "Error during registration: ${e.localizedMessage}")
                                }
                            }


                        },
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
                        Text(text = "Already have an account?", fontSize = 16.sp)
                        Text(
                            text = "Login",
                            color = backgroundColor,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .clickable {
                                   navController.navigate(Screens.login.route)
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
fun ShowData(){
    val navController = rememberNavController()
    Registration(navController)
}




