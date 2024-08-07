package com.technical.test.satoritech.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technical.test.satoritech.api.repositories.tasks.PokemonTasks
import com.technical.test.satoritech.api.utils.ApiResponseStatus
import com.technical.test.satoritech.model.Pages
import com.technical.test.satoritech.model.PokemonData
import com.technical.test.satoritech.model.PokemonList
import com.technical.test.satoritech.room.entity.PokemonEntityDB
import com.technical.test.satoritech.room.repositories.PokemonDBRepository
import com.technical.test.satoritech.utils.getIdPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val pokemonRepository: PokemonTasks,
    private val dataBaseRepository: PokemonDBRepository
) : ViewModel() {

    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
        private set

    var pokemonList = mutableStateOf<PokemonList?>(null)
        private set

    var pages = mutableStateOf(Pages("0", "25"))
        private set

    init {
        getPokemonList(pages.value)
    }

    private fun handlePokemonList(apiResponseStatus: ApiResponseStatus<PokemonList>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            apiResponseStatus.data.listPokemon.forEach {
                getPokemon(it.urlDataPokemon.getIdPokemon())
            }
        }
        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }

    private fun handlePokemonData(apiResponseStatus: ApiResponseStatus<PokemonData>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            viewModelScope.launch {
                dataBaseRepository.insertPokemonDB(
                    PokemonEntityDB(
                        apiResponseStatus.data.id,
                        apiResponseStatus.data
                    )
                )
            }
        }
        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }

    private fun getPokemonList(pages: Pages) {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handlePokemonList(pokemonRepository.getPokemonList(pages.pageInitial, pages.pageFinal))
        }
    }

    private fun getPokemon(idPokemon: String) {
        viewModelScope.launch {
            handlePokemonData(pokemonRepository.getPokemon(idPokemon))
        }
    }

    fun getPokemonListDB() {
        viewModelScope.launch {
            dataBaseRepository.getPokemonListDB()
                .collect {
                    it.forEach { pokemonDB ->

                    }
                }
        }
    }
}