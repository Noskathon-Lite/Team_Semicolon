package com.example.surakhsit_nepal.OnBoardingScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun  OnBoardingShow(onBoardingModel: OnBoardingModel){
    Column(
        modifier = Modifier.fillMaxWidth()
            .height(200.dp)
    ){

        Text(
            text = onBoardingModel.title,
            fontSize = 22.sp,
            modifier = Modifier.padding(start = 20.dp,top = 10.dp),
            color = Color.White

        )
        Text(
            text = onBoardingModel.description,
            modifier = Modifier.padding(start = 14.dp,top = 15.dp),
            color = Color.White,
            textAlign = TextAlign.Justify
        )


    }
}

@Preview(showBackground = true)
@Composable
fun PReview1(){

    OnBoardingShow(OnBoardingModel.Onboarding1)
}

@Preview(showBackground = true)
@Composable
fun PReview2(){

    OnBoardingShow(OnBoardingModel.Onboarding2)
}


@Preview(showBackground = true)
@Composable
fun PRevie21() {

    OnBoardingShow(OnBoardingModel.Onboarding3)
}