package com.technical.test.satoritech.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.technical.test.satoritech.model.User
import com.technical.test.satoritech.ui.theme.SatoriTechTheme
import com.technical.test.satoritech.ui.user.UserScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObserver()


        setContent {
            SatoriTechTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserScreen(
                        user = User(
                            "Charly Mauricio",
                            "https://st3.depositphotos.com/6672868/13701/v/450/depositphotos_137014128-stock-illustration-user-profile-icon.jpg"
                        ),
                        onClickProfile = {
                            println("Si paso el click")
                            viewModel.getPokemonListDB()
                        }
                    )
                }
            }
        }
    }

    private fun setObserver() = with(viewModel) {

    }
}