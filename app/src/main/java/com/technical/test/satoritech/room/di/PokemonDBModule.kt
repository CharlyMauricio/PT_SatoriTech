package com.technical.test.satoritech.room.di

import android.content.Context
import androidx.room.Room
import com.technical.test.satoritech.room.PokemonDataBase
import com.technical.test.satoritech.room.dao.PokemonDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokemonDBModule {

    @Provides
    @Singleton
    fun providePokemonDataBase(@ApplicationContext appContext: Context): PokemonDataBase {
        return Room.databaseBuilder(
            appContext,
            PokemonDataBase::class.java,
            "pokemon_database"
        ).build()
    }

    @Provides
    fun providePokemonDAO(appDatabase: PokemonDataBase): PokemonDAO {
        return appDatabase.pokemonDAO()
    }

}