package com.example.surakshitnepal.Components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.surakhsit_nepal.ui.theme.backgroundColor


@Composable
fun BottomBarSection(
    selectionSection : BottomSectionType,
    onSelectedSection: (BottomSectionType) ->Unit
) {



    NavigationBar(
        containerColor = backgroundColor,
    )  {
        NavigationBarItem(
            onClick = {
                onSelectedSection(BottomSectionType.Home)
            },
            icon = {Icon(Icons.Default.Home, contentDescription = "")},
            label = {Text("Home", color = Color.White)},
            selected = selectionSection ==BottomSectionType.Home
        )
        FloatingActionButton(
            onClick = {
                onSelectedSection(BottomSectionType.camera)
            },
            shape = CircleShape,
            containerColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(8.dp),
            modifier = Modifier.padding(bottom = 20.dp),
        ) {
            Image(
                imageVector = Icons.Default.CameraAlt,
                contentDescription = "",
                colorFilter = ColorFilter.tint(backgroundColor)
            )
        }


        NavigationBarItem(
            onClick = {
                onSelectedSection(BottomSectionType.Profile)
            },
            icon = {Icon(Icons.Default.Person, contentDescription = "")},
            label = {Text("Profile", color = Color.White)},
            selected = selectionSection == BottomSectionType.Profile
        )

    }
}


enum class BottomSectionType{
    Home,
    Profile,
    camera
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomBarSection() {
    var selectedSection by remember { mutableStateOf(BottomSectionType.Home) }

    BottomBarSection(
        selectionSection = selectedSection,
        onSelectedSection = { section ->
            selectedSection = section
        }
    )
}
