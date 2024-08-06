package com.technical.test.satoritech.api.dto

import com.technical.test.satoritech.api.response.PokemonListResponse
import com.technical.test.satoritech.api.response.PokemonResponse
import com.technical.test.satoritech.model.Pokemon
import com.technical.test.satoritech.model.PokemonList

class PokemonListDTOMapper {

    fun fromPokemonListDTOToPokemonListDomain(pokemonListDTO: PokemonListResponse): PokemonList {
        return PokemonList(
            pokemonListDTO.count,
            pokemonListDTO.nextPage,
            pokemonListDTO.previousPage,
            fromPokemonListDTOToPokemonListDomain(pokemonListDTO.results)
        )
    }

    private fun fromPokemonListDTOToPokemonListDomain(pokemonDTO: List<PokemonResponse>): List<Pokemon> {
        return pokemonDTO.map { fromPokemonDTOToPokemonDomain(it) }
    }

    private fun fromPokemonDTOToPokemonDomain(pokemonDTO: PokemonResponse): Pokemon {
        return Pokemon(
            pokemonDTO.namePokemon,
            pokemonDTO.urlDataPokemon
        )
    }
}