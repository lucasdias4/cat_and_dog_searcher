package com.lucasdias.data.repository.remote

import com.lucasdias.data.repository.remote.model.AnimalResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatService {
    @GET("v1/breeds/search")
    fun fetchByName(
        @Header("x-api-key") apiKey: String,
        @Query("name") name: String
    ): Single<List<AnimalResponse>>
}
