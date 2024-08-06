package com.technical.test.satoritech.api.di

import com.technical.test.satoritech.api.repositories.PokemonRepository
import com.technical.test.satoritech.api.repositories.tasks.PokemonTasks
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonTasksModule {
    @Binds
    abstract fun bindPokemonTasks(
        pokemonRepository: PokemonRepository
    ): PokemonTasks
}