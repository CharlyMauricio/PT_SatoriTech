package com.technical.test.satoritech.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.technical.test.satoritech.room.entity.PokemonEntityDB
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM Pokemon ORDER BY id ASC")
    fun getAllPokemon(): Flow<List<PokemonEntityDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(vararg pokemonEntityDB: PokemonEntityDB)

    @Delete
    fun delete(pokemonEntityDB: PokemonEntityDB)

    @Query("DELETE FROM Pokemon")
    fun deleteAllTable()
}