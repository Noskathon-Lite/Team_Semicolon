package com.example.surakhsit_nepal.MainPages.Sections



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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.surakhsit_nepal.Components.BelowNavBar
import com.example.surakhsit_nepal.Components.TopNavBar
import com.example.surakhsit_nepal.ui.theme.backgroundColor


//dummy data ko lagi

data class Notice (
    val date : String,
    val topic : String,
    val description : String
)


@Composable
fun Notices(navController : NavHostController){

    val notices = listOf(
        Notice("july 15,2025",
            "Crimes ",
            "In recent days the criminal activities are increasing, especially the women trafficking " +
                    "child abuse also."
        ),
        Notice("july 15,2025",
            "crimes",
            "In recent days the criminal activities are increasing, especially the women trafficking " +
                    "child abuse also."
        ),
        Notice("july 15,2025",
            "crimes",
            "In recent days the criminal activities are increasing, especially the women trafficking " +
                    "child abuse also."
        )
    )
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(){
            TopNavBar()
            BelowNavBar("Notices"){
                navController.popBackStack()
            }
            notices.forEach(){items ->
                CardView(items)


            }


        }

    }


}


//@Preview
@Composable
fun CardView(data : Notice) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 10.dp, start = 20.dp,end = 20.dp)
            .height(
                if(isExpanded) 100.dp else 40.dp
            )
            .clip(RoundedCornerShape(10.dp))

            .clickable {
                isExpanded = !isExpanded
            },

        colors = CardDefaults.cardColors(backgroundColor),



        ) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(text = data.topic, color = Color.White)
                Text(text = data.date, color = Color.White, fontSize = 12.sp)

            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = data.description,
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )

            }
        }


    }



}
