package com.example.marvel.network

import android.util.Log.i
import com.squareup.moshi.Json

data class Marvel (
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
    val results: List<Marvel>
)