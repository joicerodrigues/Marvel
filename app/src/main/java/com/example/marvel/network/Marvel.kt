package com.example.marvel.network

import com.squareup.moshi.Json

data class MarvelCharacters (
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnail")  val thumbnail: Thumbnail
)

data class Thumbnail(
    val path: String,
    val extension: String,
    val img: String = "$path.$extension"
)

data class MarvelListResponse(
    val data: MarvelResults
)

data class MarvelResults(
    val results: List<MarvelCharacters>
)