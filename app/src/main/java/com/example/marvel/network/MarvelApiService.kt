package com.example.marvel.network

private const val BASE_URL = // constante recebendo URL
    "https://gateway.marvel.com" // URL base da API

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build() // criando obj retrofit