package com.technical.test.satoritech.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDataList(
    val count: Int,
    val nextPage: String,
    val previousPage: String?,
    val listPokemon: List<Pokemon>
) : Parcelable