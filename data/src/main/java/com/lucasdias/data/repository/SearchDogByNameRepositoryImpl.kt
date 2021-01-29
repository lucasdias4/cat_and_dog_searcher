package com.lucasdias.data.repository

import com.lucasdias.core.resource.Resource
import com.lucasdias.data.BuildConfig.DOG_API_KEY
import com.lucasdias.data.mapper.toDomain
import com.lucasdias.data.repository.remote.DogService
import com.lucasdias.data.repository.remote.model.AnimalResponse
import com.lucasdias.domain.enum.AnimalType
import com.lucasdias.domain.model.Animal
import com.lucasdias.domain.repository.SearchDogByNameRepository

class SearchDogByNameRepositoryImpl(
    private val service: DogService
) : SearchDogByNameRepository {

    override suspend fun fetch(name: String): Resource<List<Animal>> {
        val response = Resource.listOf<AnimalResponse> {
            service.fetchByName(DOG_API_KEY, name)
        }

        return response.toDomain(AnimalType.DOG)
    }
}
