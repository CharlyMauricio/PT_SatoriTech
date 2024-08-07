package com.technical.test.satoritech.ui.main

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.technical.test.satoritech.R
import com.technical.test.satoritech.api.utils.ApiResponseStatus
import com.technical.test.satoritech.model.Pokemon
import com.technical.test.satoritech.ui.utils.BackNavigationIcon
import com.technical.test.satoritech.ui.utils.ErrorDialog
import com.technical.test.satoritech.ui.utils.LoadingScreen

private const val GRID_SPAN_COUNT = 2

@Composable
fun PokemonListScreen(
    onNavigationIconClick: () -> Unit,
    onPokemonClicked: (Pokemon) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val status = viewModel.status.value
    val pokemonList = viewModel.pokemonList.value

    Scaffold(
        topBar = { DogListScreenTopBar(onNavigationIconClick) }
    ) {
        LazyVerticalGrid(
            contentPadding = it,
            columns = GridCells.Fixed(GRID_SPAN_COUNT),
            content = {

            },
        )
    }

    if (status is ApiResponseStatus.Loading) {
        LoadingScreen()
    } else if (status is ApiResponseStatus.Error) {
        ErrorDialog(status.messageId) { }
    } else {

    }
}

@Composable
fun DogListScreenTopBar(
    onClick: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(R.string.name_app)) },
        backgroundColor = Color.White,
        contentColor = Color.Black,
        navigationIcon = { BackNavigationIcon(onClick) }
    )
}
