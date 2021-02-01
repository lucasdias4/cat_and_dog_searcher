package com.lucasdias.domain.usecase

import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.enum.RequestType
import com.lucasdias.domain.model.Animal
import com.lucasdias.domain.repository.SearchCatByNameRepository
import com.lucasdias.domain.repository.SearchDogByNameRepository
import io.reactivex.rxjava3.core.Observable

class SearchAnimalByNameAndType(
    private val searchCatByNameRepository: SearchCatByNameRepository,
    private val searchDogByNameRepository: SearchDogByNameRepository
) {
    operator fun invoke(name: String, requestType: RequestType): Observable<Resource<List<Animal>>> {
        return when (requestType) {
            RequestType.CAT -> searchCatByNameRepository.fetch(name)
            RequestType.DOG -> searchDogByNameRepository.fetch(name)
            else -> { searchCatByNameRepository.fetch(name) }
        }
    }
// TODO: Implement merge of two requests

//    private suspend fun searchBothTypesByName(name: String): Resource<List<Animal>> = coroutineScope {
//        val resources = awaitAll(
//            async { searchCatByNameRepository.fetch(name) },
//            async { searchDogByNameRepository.fetch(name) }
//        )
//
//        return@coroutineScope resources.mergeResources()
//    }
}
