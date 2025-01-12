package com.example.surakhsit_nepal.OnBoardingScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.surakhsit_nepal.Navigation.Screens
import com.example.surakhsit_nepal.R
import com.example.surakhsit_nepal.ui.theme.backgroundColor
import com.example.surakhsit_nepal.ui.theme.lightGreen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun OnBoardingScreen(navController: NavHostController){
    val pages = listOf(OnBoardingModel.Onboarding1,OnBoardingModel.Onboarding2,OnBoardingModel.Onboarding3)
    val pagerState = rememberPagerState(initialPage = 0) {
        pages.size
    }
    val scope = rememberCoroutineScope()
    Scaffold(

        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(20.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(backgroundColor),
                contentAlignment = Alignment.TopCenter,
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth()

                    ) {
                        HorizontalPager(state = pagerState) { index ->
                            OnBoardingShow(
                                onBoardingModel = pages[index]
                            )
                        }
                    }
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.BottomCenter
                    ) {

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(bottom = 20.dp)
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Indicator(totalPages = pages.size, currentPage = pagerState.currentPage)
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = if (pagerState.currentPage < pages.size - 1) "NEXT" else "GET STARTED",
                                color = Color.White,
                                modifier = Modifier
                                    .clickable {
                                        scope.launch {
                                            if(pagerState.currentPage<pages.size-1){
                                                pagerState.animateScrollToPage(pagerState.currentPage+1)

                                            } else{
                                                navController.navigate(Screens.registration.route)
                                            }

                                        }
                                    }
                            )

                            Image(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(Color.White)
                            )

                        }


                    }

                }

            }
        }
    )



    {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        listOf(
                            lightGreen,
                            Color.White
                        )
                    )
                )
        ){
            Image(
                painter =
                if(pagerState.currentPage == 0){
                    painterResource(R.drawable.video)

                }else if(pagerState.currentPage ==1){
                    painterResource(R.drawable.report)

                }else{
                    painterResource(R.drawable.nav)

                },
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 100.dp, start = 20.dp)
            )

        }

    }

}