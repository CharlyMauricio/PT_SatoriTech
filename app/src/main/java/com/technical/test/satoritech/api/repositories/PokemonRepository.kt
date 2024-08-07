package com.technical.test.satoritech.api.repositories

import com.technical.test.satoritech.api.ApiService
import com.technical.test.satoritech.api.dto.PokemonDTOMapper
import com.technical.test.satoritech.api.dto.PokemonListDTOMapper
import com.technical.test.satoritech.api.repositories.tasks.PokemonTasks
import com.technical.test.satoritech.api.utils.ApiResponseStatus
import com.technical.test.satoritech.api.utils.LIMIT_MAX_DATA
import com.technical.test.satoritech.api.utils.makeNetworkCall
import com.technical.test.satoritech.model.PokemonData
import com.technical.test.satoritech.model.PokemonList
import javax.inject.Inject

class PokemonRepository
@Inject
constructor(
    private val apiService: ApiService,
) : PokemonTasks {

    override suspend fun getPokemonList(
        pageContinue: String
    ): ApiResponseStatus<PokemonList> = makeNetworkCall {
        val response = apiService.getAllListPokemon(pageContinue, LIMIT_MAX_DATA)
        val pokemonListDTOMapper = PokemonListDTOMapper()
        pokemonListDTOMapper.fromPokemonListDTOToPokemonListDomain(response)
    }

    override suspend fun getPokemon(idPokemon: String): ApiResponseStatus<PokemonData> =
        makeNetworkCall {
            val response = apiService.getDataPokemon(idPokemon)
            val pokemonDTOMapper = PokemonDTOMapper()
            pokemonDTOMapper.fromPokemonDTOToPokemonDomain(response)
        }

}