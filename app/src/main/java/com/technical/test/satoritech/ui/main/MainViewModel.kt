package com.technical.test.satoritech.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technical.test.satoritech.api.repositories.tasks.PokemonTasks
import com.technical.test.satoritech.api.utils.ApiResponseStatus
import com.technical.test.satoritech.model.PokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val pokemonRepository: PokemonTasks,
): ViewModel() {

//    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
//        private set

    private val _status = MutableLiveData<ApiResponseStatus<Any>>()
    val status: LiveData<ApiResponseStatus<Any>>
        get() = _status

    private val _pokemonList = MutableLiveData<PokemonList>()
    val pokemonList: LiveData<PokemonList>
        get() = _pokemonList

    init {
        getPokemonList()
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<PokemonList>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            _pokemonList.value = apiResponseStatus.data!!
        }
        _status.value = apiResponseStatus as ApiResponseStatus<Any>
    }
    private fun getPokemonList(){
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(pokemonRepository.getPokemonList())
        }
    }
}