package com.technical.test.satoritech.api.response

import com.squareup.moshi.Json

data class PokemonDataResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val namePokemon: String,
    @field:Json(name = "height") val height: Int,
    @field:Json(name = "weight") val weight: Int,
    @field:Json(name = "sprites") val sprite: Sprites,
)

data class Sprites (
    @field:Json(name = "front_default") var spriteDefault: String
)