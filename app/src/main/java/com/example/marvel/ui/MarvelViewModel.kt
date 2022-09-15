package com.example.marvel.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.network.Marvel
import kotlinx.coroutines.launch
import com.example.marvel.network.MarvelApiService

enum class MarvelApiStatus {LOADING, ERROR, DONE}

class MarvelViewModel : ViewModel() {
//
//    private val _photos = MutableLiveData<Marvel>()
//    val photos: LiveData<Marvel> = _photos


    private val _status = MutableLiveData<MarvelApiStatus>() // MutableLiveData interno que armazena o status da solicitação mais recente
    val status: LiveData<MarvelApiStatus> = _status // LiveData externo imutável para o status da solicitação

    private val _marvel = MutableLiveData<Marvel>()
    val marvel: LiveData<Marvel> = _marvel

    // Isso será usado para exibir os detalhes de um heroi quando um item da lista for clicado
    private val _marvels = MutableLiveData<List<Marvel>>()
    val marvels: LiveData<List<Marvel>> = _marvels

    init{
        getMarvelList()
    }

    fun getMarvelList(){
        viewModelScope.launch {
            _status.value = MarvelApiStatus.LOADING
            try {
                _marvels.value = MarvelApiService.MarvelApi.retrofitService.getMarvel()
                _status.value = MarvelApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarvelApiStatus.ERROR
                _marvels.value = listOf()
            }
        }
    }


    fun marvelClicked(marvel: Marvel) {
        _marvel.value = marvel

    }
}