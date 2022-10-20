package com.example.marvel.ui

import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import com.example.marvel.databinding.FragmentMarvelDetailBinding
import com.example.marvel.network.MarvelApiService
import com.example.marvel.network.MarvelCharacters
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.UnknownHostException


enum class MarvelApiStatus { LOADING, NETWORK_ERROR, DONE, API_ERROR }

class MarvelViewModel : ViewModel() {

    private val _status =
        MutableLiveData<MarvelApiStatus>() // MutableLiveData interno que armazena o status da solicitação mais recente
    val status = MarvelApiStatus.DONE
      //  _status // LiveData externo imutável para o status da solicitação

    private val _marvel = MutableLiveData<MarvelCharacters>()
    val marvel: LiveData<MarvelCharacters> = _marvel

    // Isso será usado para exibir os detalhes de um heroi quando um item da lista for clicado
    private val _marvels = MutableLiveData<List<MarvelCharacters>>()
    val marvels: LiveData<List<MarvelCharacters>> = _marvels

    init {
        getMarvelList()
    }

    @VisibleForTesting
    val marvelApiService = MarvelApiService.MarvelApi.retrofitService

    fun getMarvelList() {
        viewModelScope.launch {
            _status.value = MarvelApiStatus.LOADING
            try {
                _marvels.value = marvelApiService.getMarvel().data.results
                _status.value = MarvelApiStatus.DONE
            } catch (e: Exception) {
                if (e is ConnectException || e is UnknownHostException) {
                    _status.value = MarvelApiStatus.NETWORK_ERROR
                    _marvels.value = listOf()
                } else{
                    _status.value = MarvelApiStatus.API_ERROR
                    _marvels.value = listOf()
                }
            }
        }
    }

    fun marvelClicked(marvel: MarvelCharacters) {
        _marvel.value = marvel
    }
}

//  função para verificação de conexão a internet
//    fun hasInternet(context: Context): Boolean{
//        val gerenciadorConectividade = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//        val redeAtiva: NetworkInfo? = gerenciadorConectividade.activeNetworkInfo
//        val conectado: Boolean = redeAtiva?.isConnectedOrConnecting == true
//
//        return conectado
//    }
