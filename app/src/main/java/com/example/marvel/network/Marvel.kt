package com.example.marvel.network

import com.squareup.moshi.Json

data class Marvel (
    val id: String,
    val name: String,
    val description: String,
    @Json(name = "thumbnail")  val thumbnail: String
)