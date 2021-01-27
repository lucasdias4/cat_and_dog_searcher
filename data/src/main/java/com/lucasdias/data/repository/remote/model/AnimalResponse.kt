package com.lucasdias.data.repository.remote.model

import com.google.gson.annotations.SerializedName

open class AnimalResponse(
    val id: String,
    val name: String,
    val temperament: String,
    @SerializedName("reference_image_id") val imageId: String,
    @SerializedName("wikipedia_url") val wikipediaUrl: String?
)
