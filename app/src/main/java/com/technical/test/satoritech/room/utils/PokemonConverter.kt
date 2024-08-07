package com.technical.test.satoritech.room.utils

import androidx.room.TypeConverter
import com.technical.test.satoritech.model.PokemonData
import org.json.JSONObject

class PokemonConverter {
    @TypeConverter
    fun fromSource(source: PokemonData): String {
        return JSONObject().apply {
            put("id", source.id)
            put("name", source.namePokemon)
            put("height", source.height)
            put("weight", source.weight)
            put("sprite", source.sprite)
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): PokemonData {
        val json = JSONObject(source)
        return PokemonData(
            json.getInt("id"),
            json.getString("name"),
            json.getInt("height"),
            json.getInt("weight"),
            json.getString("sprite")
        )
    }
}