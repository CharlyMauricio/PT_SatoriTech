package com.technical.test.satoritech.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonData(
    val id: Int,
    val namePokemon: String,
    val height: Int,
    val weight: Int,
    val sprite: String,
): Parcelable
