package com.lucasdias.data.repository.remote

import com.lucasdias.data.repository.remote.model.AnimalResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatService {
    @GET("v1/breeds/search")
    suspend fun fetchByName(
        @Header("x-api-key") apiKey: String,
        @Query("name") name: String
    ): List<AnimalResponse>
}
