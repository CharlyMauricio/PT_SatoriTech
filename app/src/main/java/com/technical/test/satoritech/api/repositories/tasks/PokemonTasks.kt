package com.technical.test.satoritech.api.repositories.tasks

import com.technical.test.satoritech.api.utils.ApiResponseStatus
import com.technical.test.satoritech.model.PokemonData
import com.technical.test.satoritech.model.PokemonList

interface PokemonTasks {

    suspend fun getPokemonList(pageContinue: String): ApiResponseStatus<PokemonList>
    suspend fun getPokemon(idPokemon: String): ApiResponseStatus<PokemonData>

}