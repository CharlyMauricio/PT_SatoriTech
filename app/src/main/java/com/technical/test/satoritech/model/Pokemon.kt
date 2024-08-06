package com.technical.test.satoritech.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val namePokemon: String,
    val urlDataPokemon: String
) : Parcelable