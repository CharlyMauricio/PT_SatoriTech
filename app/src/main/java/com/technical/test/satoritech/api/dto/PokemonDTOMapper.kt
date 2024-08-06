package com.technical.test.satoritech.api.dto

import com.technical.test.satoritech.api.response.PokemonDataResponse
import com.technical.test.satoritech.model.PokemonData

class PokemonDTOMapper {
    fun fromPokemonDTOToPokemonDomain(pokemonDTO: PokemonDataResponse): PokemonData {
        return PokemonData(
            pokemonDTO.id,
            pokemonDTO.namePokemon,
            pokemonDTO.height,
            pokemonDTO.weight,
            pokemonDTO.sprite.spriteDefault
        )
    }

}