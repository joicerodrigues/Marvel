package com.example.marvel.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

private const val BASE_URL = // constante recebendo URL
    "https://gateway.marvel.com" // URL base da API

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi)) //conversor moshi
    .baseUrl(BASE_URL) // indicando/chamando URL base declarada na constante
    .client(OkHttpClient()
        .newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build())
    .build() // criante um obj retrofi //


class MarvelApiService {
    interface MarvelApiService {
        @GET("v1/public/characters?ts=1&apikey=f9280878de62b54189a58267e86eb58c&hash=bb2241fb22f0b97384027288832ec8f0") // informando que é uma solicitação get com o @GET
       suspend fun getMarvel(): MarvelListResponse // recebe strings de resposta do web service
    }
    object MarvelApi {
        val retrofitService: MarvelApiService by lazy { // inicialização lenta
            retrofit.create(MarvelApiService::class.java)
        }
    }
}