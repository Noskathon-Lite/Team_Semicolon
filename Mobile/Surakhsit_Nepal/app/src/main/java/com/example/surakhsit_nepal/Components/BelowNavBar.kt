package com.example.surakhsit_nepal.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.surakhsit_nepal.ui.theme.backgroundColor




@Composable
fun BelowNavBar(text : String,onClick :  () ->Unit){
    Box(
        modifier = Modifier.fillMaxWidth()
            .background(backgroundColor)
            .height(50.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Image(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.clickable {
                    onClick()
                }
            )
            Text(
                text = text,
                color = Color.White,
                fontSize = 22.sp
            )

        }
    }
}