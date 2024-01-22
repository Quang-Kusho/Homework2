package com.example.homework1.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.homework1.ui.screen.FirstScreen
import com.example.homework1.ui.screen.SecondScreen

@Composable
fun App(
    appState: AppState = rememberState()
){
    NavHost(
        navController = appState.navController,
        startDestination = "first"
    ){
        composable(route = "second"){
            FirstScreen(onBackPress = appState::navigateBack)
        }
        composable(route = "first"){
            SecondScreen(name = "abc", text = "123", navController = appState.navController)
        }
    }
}