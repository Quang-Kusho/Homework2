package com.example.homework1.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homework1.R
import com.example.homework1.ui.AppViewModel
import com.example.homework1.ui.screen.Details
import com.example.homework1.ui.screen.Edit
import com.example.homework1.ui.screen.Entry
import com.example.homework1.ui.screen.Home

enum class AppScreen(@StringRes val title: Int) {
    Home(title = R.string.home),
    Entry(title = R.string.entry),
    Details(title = R.string.details),
    Edit(title = R.string.edit)
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: AppViewModel
) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.Home.name,
        modifier = modifier
    ) {
        composable(route = AppScreen.Home.name) {
            Home(
                navigateToAccountEntry = {
                    viewModel.resetAccountUiState()
                    navController.navigate(route = AppScreen.Entry.name)
                },
                navigateToAccountUpdate = {
                    viewModel.updateAccountUiState(it)
                    navController.navigate(route = AppScreen.Details.name)
                },
                viewModel = viewModel
            )
        }
        composable(route = AppScreen.Entry.name) {
            Entry(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                viewModel = viewModel
            )
        }
        composable(route = AppScreen.Details.name) {
            Details(
                navigateToEditAccount = {
                    viewModel.updateAccountUiState(it)
                    navController.navigate(route = AppScreen.Edit.name)
                },
                navigateBack = { navController.navigateUp() },
                viewModel = viewModel
            )
        }
        composable(route = AppScreen.Edit.name) {
            Edit(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                viewModel = viewModel
            )
        }
    }
}