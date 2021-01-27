package com.lucasdias.domain.usecase

import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.enum.RequestType
import com.lucasdias.domain.model.Animal
import com.lucasdias.domain.repository.SearchCatByNameRepository
import com.lucasdias.domain.repository.SearchDogByNameRepository

class SearchAnimalByNameAndType(
    private val searchCatByNameRepository: SearchCatByNameRepository,
    private val searchDogByNameRepository: SearchDogByNameRepository
) {
    suspend operator fun invoke(name: String, requestType: RequestType): Resource<List<Animal>> {
        return when (requestType) {
            RequestType.CAT -> searchCatByNameRepository.fetch(name)
            RequestType.DOG -> searchDogByNameRepository.fetch(name)
            else -> searchCatByNameRepository.fetch(name)
        }
    }
}
