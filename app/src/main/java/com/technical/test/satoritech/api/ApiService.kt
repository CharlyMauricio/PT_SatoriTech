package com.technical.test.satoritech.api

import com.technical.test.satoritech.api.response.PokemonDataResponse
import com.technical.test.satoritech.api.response.PokemonListResponse
import com.technical.test.satoritech.api.utils.GET_ALL_POKEMON
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(GET_ALL_POKEMON)
    suspend fun getAllListPokemon(
        @Query("offset") nextGroup: String,
        @Query("limit") limit: String,
    ): PokemonListResponse

    @GET("$GET_ALL_POKEMON/{idPokemon}")
    suspend fun getDataPokemon(
        @Path("idPokemon") idPokemon: String,
    ): PokemonDataResponse
}