package com.technical.test.satoritech.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.technical.test.satoritech.room.dao.PokemonDAO
import com.technical.test.satoritech.room.entity.PokemonEntityDB
import com.technical.test.satoritech.room.utils.PokemonConverter

@Database(entities = [PokemonEntityDB::class], version = 1)
@TypeConverters(PokemonConverter::class)
abstract class PokemonDataBase : RoomDatabase() {

    abstract fun pokemonDAO(): PokemonDAO

}