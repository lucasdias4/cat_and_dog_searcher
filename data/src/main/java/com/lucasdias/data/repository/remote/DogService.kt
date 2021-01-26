package com.lucasdias.data.repository.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface DogService {
    @GET("v1/breeds/search")
    suspend fun fetchByName(
        @Header("x-api-key") apiKey: String,
        @Query("name") name: String
    ): Response<Any?>
}
