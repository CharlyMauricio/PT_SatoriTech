package com.technical.test.satoritech.ui.navigations

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.technical.test.satoritech.model.PokemonData
import com.technical.test.satoritech.ui.navigations.NavDestinations.PokemonDetailScreenDestination
import com.technical.test.satoritech.ui.navigations.NavDestinations.PokemonListScreenDestination
import com.technical.test.satoritech.ui.screens.detail.PokemonDetailsScreen
import com.technical.test.satoritech.ui.screens.list.PokemonListScreen
import com.technical.test.satoritech.ui.screens.main.MainViewModel

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun MainNavigationScreen(
    onBackClick: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        onBackClick = { onBackClick() },
        mainViewModel = viewModel,
    )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
private fun NavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    mainViewModel: MainViewModel
) {
    lateinit var pokemonData: PokemonData
    NavHost(
        navController = navController,
        startDestination = PokemonListScreenDestination
    ) {
        composable(route = PokemonListScreenDestination) {
            PokemonListScreen(
                user = mainViewModel.dataUser().value,
                onBackClick = { onBackClick() },
                onClickUserProfile = {
                    Log.d("TESTER", "onClickUserProfile")
                },
                onPokemonClicked = {
                    Log.d("TESTER", "onPokemonClicked")
                    pokemonData = it
                    navController.navigate(route = PokemonDetailScreenDestination)
                }
            )
        }

        composable(route = PokemonDetailScreenDestination) {
            PokemonDetailsScreen(
                pokemonData,
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}