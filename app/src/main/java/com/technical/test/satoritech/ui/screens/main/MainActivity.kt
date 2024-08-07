package com.technical.test.satoritech.ui.screens.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.remember
import coil.annotation.ExperimentalCoilApi
import com.technical.test.satoritech.model.User
import com.technical.test.satoritech.ui.navigations.MainNavigationScreen
import com.technical.test.satoritech.ui.screens.list.PokemonListScreen
import com.technical.test.satoritech.ui.theme.SatoriTechTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SatoriTechTheme {
                MainNavigationScreen(
                    onBackClick = { finish()}
                )
            }
        }
    }

}