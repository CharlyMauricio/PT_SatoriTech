package com.technical.test.satoritech.api.repositories.tasks

import com.technical.test.satoritech.api.utils.ApiResponseStatus
import com.technical.test.satoritech.model.PokemonList

interface PokemonTasks {
    suspend fun getPokemonList(): ApiResponseStatus<PokemonList>

}