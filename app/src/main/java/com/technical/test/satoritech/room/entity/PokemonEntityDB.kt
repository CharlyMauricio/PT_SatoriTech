package com.technical.test.satoritech.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.technical.test.satoritech.model.PokemonData

@Entity(tableName = "pokemon")
data class PokemonEntityDB(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "pokemon_data")
    val pokemon: PokemonData
)