package com.technical.test.satoritech.api

import com.technical.test.satoritech.api.response.PokemonListResponse
import com.technical.test.satoritech.api.utils.GET_ALL_POKEMON
import retrofit2.http.GET

interface ApiService {
    @GET(GET_ALL_POKEMON)
    suspend fun getAllListPokemon(): PokemonListResponse
}