package com.technical.test.satoritech.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
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
        ErrorDialog(status.messageId) {  }
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
