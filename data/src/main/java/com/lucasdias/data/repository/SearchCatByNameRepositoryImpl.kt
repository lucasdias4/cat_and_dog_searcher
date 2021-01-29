package com.lucasdias.data.repository

import com.lucasdias.core.resource.Resource
import com.lucasdias.data.BuildConfig.CAT_API_KEY
import com.lucasdias.data.mapper.toDomain
import com.lucasdias.data.repository.remote.CatService
import com.lucasdias.data.repository.remote.model.AnimalResponse
import com.lucasdias.domain.enum.AnimalType
import com.lucasdias.domain.model.Animal
import com.lucasdias.domain.repository.SearchCatByNameRepository

class SearchCatByNameRepositoryImpl(
    private val service: CatService
) : SearchCatByNameRepository {

    override suspend fun fetch(name: String): Resource<List<Animal>> {
        val response = Resource.listOf<AnimalResponse> {
                service.fetchByName(CAT_API_KEY, name)
            }

        return response.toDomain(AnimalType.CAT)
    }
}
