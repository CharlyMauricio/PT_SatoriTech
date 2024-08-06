package com.technical.test.satoritech.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technical.test.satoritech.api.repositories.tasks.PokemonTasks
import com.technical.test.satoritech.api.utils.ApiResponseStatus
import com.technical.test.satoritech.model.PokemonData
import com.technical.test.satoritech.model.PokemonList
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
): ViewModel() {

    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
        private set

    var pokemonList = mutableStateOf<PokemonList?>(null)
        private set

    init {
        getPokemonList("0","25")
    }

/*
    private fun <T> handleResponseStatus(apiResponseStatus: ApiResponseStatus<>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            if (apiResponseStatus.data is PokemonList){
                apiResponseStatus.data.listPokemon.forEach {
                    getPokemon(it.urlDataPokemon.getIdPokemon())
                }
            }
        }
        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }
*/

    private fun handlePokemonList(apiResponseStatus: ApiResponseStatus<PokemonList>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            getPokemon("1")
            /*apiResponseStatus.data.listPokemon.forEach {
                getPokemon(it.urlDataPokemon.getIdPokemon())
            }*/
        }
        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }

    private fun handlePokemonData(apiResponseStatus: ApiResponseStatus<PokemonData>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {

        }
        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }

    private fun getPokemonList(pageMin: String, pageMax: String){
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handlePokemonList(pokemonRepository.getPokemonList(pageMin, pageMax))
        }
    }

    private fun getPokemon(idPokemon: String){
        viewModelScope.launch {
            handlePokemonData(pokemonRepository.getPokemon(idPokemon))
        }
    }
}