package com.technical.test.satoritech.api.response

import com.squareup.moshi.Json

data class PokemonListResponse(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "next") val nextPage: String,
    @field:Json(name = "previous") val previousPage: String,
    @field:Json(name = "results") val results: List<PokemonResponse>,
)

data class PokemonResponse(
    @field:Json(name = "name") val namePokemon: String,
    @field:Json(name = "url") val urlDataPokemon: String,
)