package com.example.surakhsit_nepal.MainPages.Sections


import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonPinCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.surakhsit_nepal.Backend.BackendData.BackendViewModel
import com.example.surakhsit_nepal.Components.BelowNavBar
import com.example.surakhsit_nepal.Components.TopNavBar
import com.example.surakhsit_nepal.CriminalDatabase.criminalsItem
import com.example.surakhsit_nepal.ui.theme.backgroundColor


@Composable
fun WantedList(navController : NavHostController,viewModel : BackendViewModel = viewModel()){

    //yetanera ko criminals ko lagi
    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzY4MjA4OTg1LCJpYXQiOjE3MzY2NzI5ODUsImp0aSI6IjY0MThmN2M2ZGZhYjQ1NWNhZGI3YjEzNGJhZDU1MjE1IiwidXNlcl9pZCI6Mn0._dTs6iszpk7sergxY4e-QsX9cIevCq0AZkf68l0RsLg"
    LaunchedEffect(token) {
        viewModel.fetchCriminals(token)
    }
    val criminals = viewModel.criminals.collectAsState()
    if (criminals.value.isEmpty()) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize().wrapContentSize())
        Log.e("why","${criminals.value}")
    } else {


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column() {
            TopNavBar()
            BelowNavBar("Wanted List") {
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
                items(criminals.value) { items ->
                    CriminalItem(items)

                }
            }


        }
    }



    }

}


////data : criminalsItem
//@Composable
//fun WantedDataShow(data : Cr){
//    var isExpanded by remember { mutableStateOf(false) }
//
//    Card(
//        modifier = Modifier.fillMaxWidth(.4f)
//            .padding(top = 10.dp)
//            .height(
//                if(isExpanded) 150.dp else 90.dp
//            )
//            .clip(RoundedCornerShape(10.dp))
//
//            .clickable {
//                isExpanded = !isExpanded
//            },
//
//        colors = CardDefaults.cardColors(backgroundColor)
//    )
//    {
//        Column(
//            modifier = Modifier.fillMaxWidth(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Image(
//                imageVector = Icons.Default.PersonPinCircle,
//                contentDescription = "image",
//                colorFilter = ColorFilter.tint(Color.White),
//                modifier = Modifier.size(60.dp)
//
//
//
//            )
//            if(isExpanded){
//                Spacer(modifier = Modifier.height(20.dp))
//                Column(){
//                    Text(text = "Name : janak", color = Color.White)
//                    Text(text = "Age : 32", color = Color.White)
//                    Text(text = "Case : Robbery", color = Color.White)
//
//                }
//            }
//
//        }
//    }
//
//}

@Composable
fun CriminalItem(criminal: criminalsItem) {
    val fullImageUrl = "http://192.168.23.8:8000" + criminal.image
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(fullImageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = "Name: ${criminal.name}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Age: ${criminal.case}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Case: ${criminal.age}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

//preview dekhauna laii

@Preview(showBackground = true)
@Composable
fun showCriminals(){
    val navController = rememberNavController()
    WantedList(navController)
}