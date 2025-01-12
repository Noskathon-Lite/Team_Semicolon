package com.example.surakhsit_nepal.OnBoardingScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Indicator(
    totalPages : Int,
    currentPage : Int,
    SelectedColor : Color = Color.Blue,
    UnselectedColor : Color = Color.White
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        repeat(totalPages){
            Box(
                modifier = Modifier
                    .height(14.dp)
                    .width(if(it == currentPage) 12.dp else 12.dp)
                    .clip(CircleShape)
                    .background(color = if(it == currentPage) SelectedColor else UnselectedColor )
            )
            Spacer(modifier = Modifier.size(30.dp))
        }

    }

}

@Preview(showBackground = true)
@Composable
fun Preview1(){
    Indicator(3,0)
}

@Preview(showBackground = true)
@Composable
fun Preview2(){
    Indicator(3,1)
}

@Preview(showBackground = true)
@Composable
fun Preview3(){
    Indicator(3,2)
}