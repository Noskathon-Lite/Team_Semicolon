package com.example.surakhsit_nepal.MainPages.Sections


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.PersonPinCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.surakhsit_nepal.Components.BelowNavBar
import com.example.surakhsit_nepal.Components.TopNavBar
import com.example.surakhsit_nepal.ui.theme.backgroundColor


@Composable
fun WantedList(navController : NavHostController){
   // val criminals = viewModel.criminals.collectAsState()
    val value = 3
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column() {
            TopNavBar()
            BelowNavBar("Wanted List"){
                navController.popBackStack()
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)

            ) {
               items(value){items ->
                    WantedDataShow()

                }
            }


        }



    }

}


//data : criminalsItem
@Composable
fun WantedDataShow(){
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(.4f)
            .padding(top = 10.dp)
            .height(
                if(isExpanded) 150.dp else 90.dp
            )
            .clip(RoundedCornerShape(10.dp))

            .clickable {
                isExpanded = !isExpanded
            },

        colors = CardDefaults.cardColors(backgroundColor)
    )
    {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = Icons.Default.PersonPinCircle,
                contentDescription = "image",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(60.dp)



            )
            if(isExpanded){
                Spacer(modifier = Modifier.height(20.dp))
                Column(){
                    Text(text = "Name : janak", color = Color.White)
                    Text(text = "Age : 32", color = Color.White)
                    Text(text = "Case : Robbery", color = Color.White)

                }
            }

        }
    }

}