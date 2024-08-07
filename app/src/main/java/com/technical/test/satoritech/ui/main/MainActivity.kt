package com.technical.test.satoritech.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.technical.test.satoritech.model.User
import com.technical.test.satoritech.ui.theme.SatoriTechTheme
import com.technical.test.satoritech.ui.user.UserScreen
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SatoriTechTheme {
                PokemonListScreen(
                    onNavigationIconClick = { /*TODO*/ },
                    onPokemonClicked = {}
                )
            }
        }
    }

}