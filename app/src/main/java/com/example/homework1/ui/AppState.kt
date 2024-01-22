package com.example.homework1.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class AppState(
    val navController: NavHostController
){
    fun navigateBack(){
        navController.popBackStack()
    }
}

@Composable
fun rememberState(
    navController: NavHostController = rememberNavController()
) = remember(navController){
    AppState(navController)
}