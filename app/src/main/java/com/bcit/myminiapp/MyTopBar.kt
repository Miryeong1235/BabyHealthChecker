package com.bcit.myminiapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BabyChangingStation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.material.icons.outlined.EmojiEmotions
import androidx.compose.material.icons.outlined.NoFood
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navController: NavController) {

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate("home")
                }
            ) {
                Icon(imageVector = Icons.Outlined.BabyChangingStation, contentDescription = "Poop Icon")
            }
        },
        title = {
            Text("BabyHealthChecker", fontSize = 25.sp)
        },
        actions = {
            IconButton(
                onClick = {
                    navController.navigate("allergyInfo")
                }
            ) {
                Icon(imageVector = Icons.Outlined.NoFood, contentDescription = "No Food Icon")
            }
        },

    )
}