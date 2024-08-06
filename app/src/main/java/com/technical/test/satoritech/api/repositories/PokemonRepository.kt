package com.technical.test.satoritech.api.repositories

import com.technical.test.satoritech.api.ApiService
import com.technical.test.satoritech.api.dto.PokemonListDTOMapper
import com.technical.test.satoritech.api.repositories.tasks.PokemonTasks
import com.technical.test.satoritech.api.utils.ApiResponseStatus
import com.technical.test.satoritech.api.utils.makeNetworkCall
import com.technical.test.satoritech.model.PokemonList
import javax.inject.Inject

class PokemonRepository
@Inject
constructor(
    private val apiService: ApiService,
) : PokemonTasks {
    override suspend fun getPokemonList(): ApiResponseStatus<PokemonList> = makeNetworkCall {
        val response = apiService.getAllListPokemon()
        val pokemonListDTOMapper = PokemonListDTOMapper()
        pokemonListDTOMapper.fromPokemonListDTOToPokemonListDomain(response)
    }
}