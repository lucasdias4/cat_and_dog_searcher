package com.lucasdias.domain.usecase

import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.repository.SearchCatByNameRepository
import com.lucasdias.domain.repository.SearchDogByNameRepository

class SearchAnimalByNameAndType(
    private val searchCatByNameRepository: SearchCatByNameRepository,
    private val searchDogByNameRepository: SearchDogByNameRepository
) {
    suspend operator fun invoke(name: String): Resource<Any?> {
        return searchCatByNameRepository.fetch(name)
    }
}
