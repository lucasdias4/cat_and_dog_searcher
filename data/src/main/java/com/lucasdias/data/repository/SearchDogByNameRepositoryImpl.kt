package com.lucasdias.data.repository

import android.util.Log
import com.lucasdias.core.resource.Resource
import com.lucasdias.data.BuildConfig.DOG_API_KEY
import com.lucasdias.data.repository.remote.DogService
import com.lucasdias.domain.repository.SearchDogByNameRepository
import retrofit2.Response

class SearchDogByNameRepositoryImpl(
    private val service: DogService
) : SearchDogByNameRepository {

    override suspend fun fetch(name: String): Resource<Any?> {
        val response: Resource<Response<Any?>> =
            Resource.of {
                service.fetchByName(DOG_API_KEY, name)
            }

        Log.i("Search dog", "RESPONSE: ${response.value()}")
        return Resource.Success(response)
    }
}
