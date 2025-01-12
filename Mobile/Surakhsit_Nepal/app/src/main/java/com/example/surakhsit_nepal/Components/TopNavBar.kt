package com.example.surakhsit_nepal.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.surakhsit_nepal.ui.theme.navyBlue

@Preview(showBackground = true)
@Composable
fun TopNavBar()
{
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(40.dp)
            .background(navyBlue)
    ){}
}