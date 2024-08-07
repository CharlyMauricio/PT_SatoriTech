@file:OptIn(ExperimentalMaterialApi::class)

package com.technical.test.satoritech.ui.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.technical.test.satoritech.api.utils.ApiResponseStatus
import com.technical.test.satoritech.model.PokemonData
import com.technical.test.satoritech.model.User
import com.technical.test.satoritech.ui.screens.main.MainViewModel
import com.technical.test.satoritech.ui.utils.composable.ErrorDialog
import com.technical.test.satoritech.ui.utils.composable.LoadingScreen
import com.technical.test.satoritech.ui.utils.composable.TopBarNavigation
import com.technical.test.satoritech.ui.utils.firstCharUpperCase
import com.technical.test.satoritech.ui.utils.getInitials

private const val GRID_SPAN_COUNT = 1

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun PokemonListScreen(
    user: User,
    onBackClick: () -> Unit,
    onClickUserProfile: () -> Unit,
    onPokemonClicked: (PokemonData) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val status = viewModel.status.value
    val pokemonList = viewModel.pokemonDataList.collectAsState().value

    Scaffold(
        topBar = { ScreenTopBar(
            user,
            onBackClick,
            onClickUserProfile
        ) }
    ) {
        LazyVerticalGrid(
            contentPadding = it,
            columns = GridCells.Fixed(GRID_SPAN_COUNT),
            content = {
                items(pokemonList){ pokemon ->
                    PokemonGridItem(pokemon, onPokemonClicked)
                }
            },
        )
    }

    when (status) {
        is ApiResponseStatus.Loading -> {
            LoadingScreen()
        }
        is ApiResponseStatus.Error -> {
            ErrorDialog(status.messageId) { }
        }
        else -> {}
    }
}

@Composable
fun ScreenTopBar(
    user: User,
    onClick: () -> Unit,
    onClickUserProfile: () -> Unit,
) {
    TopAppBar(
        title = { TopBarNavigation(
            user,
            onClick,
            onClickUserProfile
        ) },
        backgroundColor = Color.White,
        contentColor = Color.Black
    )
}

@Composable
fun PokemonGridItem(pokemonData: PokemonData, onPokemonClicked: (PokemonData) -> Unit) {
    if (pokemonData.sprite.isNullOrEmpty()) {
        Surface(
            modifier = Modifier
                .padding(8.dp)
                .height(100.dp)
                .width(100.dp),
            color = Color.Red,
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = pokemonData.namePokemon.getInitials(),
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 42.sp,
                fontWeight = FontWeight.Black
            )
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    onPokemonClicked(pokemonData)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(pokemonData.sprite),
                contentDescription = null,
                modifier = Modifier
                    .background(Color.White)
                    .size(100.dp)
                    .semantics { testTag = "pokemon-${pokemonData.namePokemon}" }
            )
            Text(
                text = pokemonData.namePokemon.firstCharUpperCase(),
                color = Color.Black,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontWeight = FontWeight.Black
            )
        }
    }
}
