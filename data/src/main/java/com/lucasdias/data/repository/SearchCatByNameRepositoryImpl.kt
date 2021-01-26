package com.lucasdias.data.repository

import android.util.Log
import com.lucasdias.core.resource.Resource
import com.lucasdias.data.BuildConfig.CAT_API_KEY
import com.lucasdias.data.repository.remote.CatService
import com.lucasdias.domain.repository.SearchCatByNameRepository
import retrofit2.Response

class SearchCatByNameRepositoryImpl(
    private val service: CatService
) : SearchCatByNameRepository {

    override suspend fun fetch(name: String): Resource<Any?> {
        val response: Resource<Response<Any?>> =
            Resource.of {
                service.fetchByName(CAT_API_KEY, name)
            }

        Log.i("Search cat", "RESPONSE: ${response.value()}")
        return Resource.Success(response)
    }
}
