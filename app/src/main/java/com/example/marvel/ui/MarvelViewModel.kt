package com.example.marvel.ui

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.network.MarvelApiService
import com.example.marvel.network.MarvelCharacters
import kotlinx.coroutines.launch


enum class MarvelApiStatus {LOADING, ERROR, DONE, DISCONECT }

class MarvelViewModel : ViewModel() {

    private val _status = MutableLiveData<MarvelApiStatus>() // MutableLiveData interno que armazena o status da solicitação mais recente
    val status: LiveData<MarvelApiStatus> = _status // LiveData externo imutável para o status da solicitação

    private val _marvel = MutableLiveData<MarvelCharacters>()
    val marvel: LiveData<MarvelCharacters> = _marvel

    // Isso será usado para exibir os detalhes de um heroi quando um item da lista for clicado
    private val _marvels = MutableLiveData<List<MarvelCharacters>>()
    val marvels: LiveData<List<MarvelCharacters>> = _marvels

    init{
        getMarvelList()
    }

    fun getMarvelList(){
        viewModelScope.launch {
            _status.value = MarvelApiStatus.LOADING
            try {
                _marvels.value = MarvelApiService.MarvelApi.retrofitService.getMarvel().data.results
                _status.value = MarvelApiStatus.DONE
            } catch (e: Exception) {
                if (hasInternet()) {
                    _status.value = MarvelApiStatus.ERROR
                    _marvels.value = listOf()
                }else{
                    _status.value = MarvelApiStatus.DISCONECT
                    _marvels.value = listOf()
                }

            }
        }
    }

    fun marvelClicked(marvel: MarvelCharacters) {
        _marvel.value = marvel
    }



    fun hasInternet(context: Context): Boolean{

        val gerenciadorConectividade = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = gerenciadorConectividade.activeNetworkInfo
        val conectado: Boolean = activeNetwork?.isConnectedOrConnecting == true

        return conectado

    }

}