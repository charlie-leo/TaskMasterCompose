package com.task.master.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

import kotlinx.coroutines.launch

/**
 * Created by Charles Raj I on 16/06/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomNavigationScreen() {

    var currentRoute by remember {
        mutableStateOf("home")
    }
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val items = listOf(
        BottomNavItem("Home", "home", Icons.Filled.Home),
        BottomNavItem("Tasks", "task", Icons.Filled.Star),
        BottomNavItem("Notification", "notification", Icons.Filled.Notifications),
        BottomNavItem("Profile", "profile", Icons.Filled.Person),
    )
    Scaffold(
        bottomBar = {
            BottomNavigationBar(items, pagerState.currentPage){ page ->
                scope.launch {
                    pagerState.animateScrollToPage(page)
                }
            }
        }
    ) { paddingValues ->

        HorizontalPager(count = items.size,
            state = pagerState,
            modifier = Modifier.padding(paddingValues)
        ) { page ->

            when (page) {
                0 -> ScreenOne(paddingValues, Color.Red)
                1 -> ScreenOne(paddingValues, Color.Gray)
                2 -> ScreenOne(paddingValues, Color.Green)
                3 -> ScreenOne(paddingValues, Color.Magenta)
            }
        }
    }
}


@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem> = listOf(),
    currentRoute: Int,
    onItemClick: (Int) -> Unit
) {

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                alwaysShowLabel = currentRoute == index,
                selected = currentRoute == index,
                onClick = { onItemClick(index) }
            )
        }
    }
}

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector
)

@Composable
fun ScreenOne(paddingValues: PaddingValues, color: Color) {



    Column (modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues.calculateBottomPadding())
        .background(color = color)) {

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomNavigationScreenPreview() {
    BottomNavigationScreen()
}