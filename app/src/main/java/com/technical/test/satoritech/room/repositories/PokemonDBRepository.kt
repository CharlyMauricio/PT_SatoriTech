package com.technical.test.satoritech.room.repositories

import com.technical.test.satoritech.room.dao.PokemonDAO
import com.technical.test.satoritech.room.entity.PokemonEntityDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonDBRepository
@Inject
constructor(
    private val pokemonDAO: PokemonDAO,
) {

    fun getPokemonListDB(): Flow<List<PokemonEntityDB>> {
        return pokemonDAO.getAllPokemon()
    }

    suspend fun insertPokemonDB(pokemonEntityDB: PokemonEntityDB) {
        withContext(Dispatchers.IO) {
            pokemonDAO.insertPokemon(pokemonEntityDB)
        }
    }
}