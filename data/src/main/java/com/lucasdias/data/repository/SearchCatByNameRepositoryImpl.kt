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
        val response: Resource<List<AnimalResponse>> =
            Resource.of {
                service.fetchByName(CAT_API_KEY, name)
            }

        return response.getTreatedResponse()
    }

    private fun Resource<List<AnimalResponse>>.getTreatedResponse(): Resource<List<Animal>> {
        this.error()?.let {
            return Resource.Error(it)
        }

        this.value()?.let {
            if (it.isEmpty()) return Resource.SuccessWithoutContent()

            val animals = it.toDomain(AnimalType.CAT)

            return Resource.Success(animals)
        } ?: run {
            return Resource.SuccessWithoutContent()
        }
    }
}
