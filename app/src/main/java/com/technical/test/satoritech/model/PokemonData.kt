package com.technical.test.satoritech.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonData(
    var type: String
): Parcelable
