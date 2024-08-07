package com.technical.test.satoritech.ui.screens.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technical.test.satoritech.api.repositories.tasks.PokemonTasks
import com.technical.test.satoritech.api.utils.ApiResponseStatus
import com.technical.test.satoritech.model.Pages
import com.technical.test.satoritech.model.PokemonData
import com.technical.test.satoritech.model.PokemonList
import com.technical.test.satoritech.model.User
import com.technical.test.satoritech.room.entity.PokemonEntityDB
import com.technical.test.satoritech.room.repositories.PokemonDBRepository
import com.technical.test.satoritech.ui.utils.getIdPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private var _pokemonDataList = MutableStateFlow<MutableList<PokemonData>>(mutableListOf())
    val pokemonDataList: StateFlow<MutableList<PokemonData>>
        get() = _pokemonDataList

    init {
        //deleteAllPokemon()
        getPokemonListDB()
    }

    fun dataUser() = mutableStateOf(User("Carlos Mauricio", ""))

    fun getPokemonList(pageContinue: String) {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handlePokemonList(pokemonRepository.getPokemonList(pageContinue))
        }
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
                getPokemonListDB()
            }
        }
        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }

    private fun getPokemon(idPokemon: String) {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handlePokemonData(pokemonRepository.getPokemon(idPokemon))
        }
    }

    private fun getPokemonListDB() {
        viewModelScope.launch {
            dataBaseRepository.getPokemonListDB()
                .collect { pokemonDataDB ->
                    if (pokemonDataDB.isNotEmpty()) {
                        val pokemonData = _pokemonDataList.value.toMutableList()
                        pokemonDataDB.forEach {
                            pokemonData.add(it.pokemon)
                        }
                        _pokemonDataList.value = pokemonData
                    } else {
                        getPokemonList("0")
                    }
                }
        }
    }

    private fun deleteAllPokemon() {
        viewModelScope.launch {
            dataBaseRepository.deleteAllPokemon()
        }
    }
}