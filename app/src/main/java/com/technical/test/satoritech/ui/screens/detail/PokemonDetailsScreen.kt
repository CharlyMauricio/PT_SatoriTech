package com.technical.test.satoritech.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import coil.compose.rememberAsyncImagePainter
import com.technical.test.satoritech.R
import com.technical.test.satoritech.model.PokemonData
import com.technical.test.satoritech.ui.screens.main.MainViewModel
import com.technical.test.satoritech.ui.utils.composable.BackNavigationIcon
import com.technical.test.satoritech.ui.utils.firstCharUpperCase

@Composable
fun PokemonDetailsScreen(
    pokemonData: PokemonData,
    onBackClick: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            ScreenTopBar(
                pokemonData.namePokemon,
                onBackClick
            )
        }
    ) { paddingValues ->
        PokemonItem(paddingValues, pokemonData)
    }
}

@Composable
fun ScreenTopBar(
    namePokemon: String,
    onClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(stringResource(R.string.details_title_format, namePokemon.firstCharUpperCase())) },
        backgroundColor = Color.White,
        contentColor = Color.Black,
        navigationIcon = {
            BackNavigationIcon(
                onClick,
            )
        }
    )
}

@Composable
fun PokemonItem(paddingValues: PaddingValues, pokemonData: PokemonData) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(pokemonData.sprite),
            contentDescription = null,
            modifier = Modifier
                .background(Color.White)
                .size(200.dp)
                .semantics { testTag = "pokemon-${pokemonData.namePokemon}" }
        )
        Text(
            text = pokemonData.namePokemon.firstCharUpperCase(),
            color = Color.Black,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Black
        )
        Text(
            text = stringResource(R.string.height_format, pokemonData.height),
            color = Color.Black,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Black
        )
        Text(
            text = stringResource(R.string.weight_format, pokemonData.weight),
            color = Color.Black,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Black
        )
    }
}
