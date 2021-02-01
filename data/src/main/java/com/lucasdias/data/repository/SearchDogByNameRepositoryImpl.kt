package com.lucasdias.data.repository

import com.lucasdias.core.resource.Resource
import com.lucasdias.data.BuildConfig.DOG_API_KEY
import com.lucasdias.data.mapper.toDomain
import com.lucasdias.data.repository.remote.DogService
import com.lucasdias.domain.enum.AnimalType
import com.lucasdias.domain.model.Animal
import com.lucasdias.domain.repository.SearchDogByNameRepository
import io.reactivex.rxjava3.core.Observable

class SearchDogByNameRepositoryImpl(
    private val service: DogService
) : SearchDogByNameRepository {

    //TODO: Implement .startWith
    override fun fetch(name: String): Observable<Resource<List<Animal>>> {
        return service.fetchByName(DOG_API_KEY, name)
            .toObservable()
            .map {
                it.toDomain(AnimalType.DOG)
            }
            .onErrorReturn {
                Resource.Error(it)
            }
    }
}
