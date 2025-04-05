package com.bcit.myminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


/**
 * Misuzu Taniguchi, A01367008
 */

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(modifier = Modifier.safeDrawingPadding()) {
                MainContent()
            }
        }
    }

    @Composable
    fun MainContent(){
        val navController = rememberNavController()

        Scaffold(
            topBar = {
                MyTopBar(navController)
            }
        ) { padding ->
            NavHost(navController,
                "home",
                modifier = Modifier.padding(padding)
            ){
                composable("home") {
                    Home()
                }

                composable("allergyInfo") {
                    AllergyInfo()
                }
            }

        }
    }

}


