package com.lucasdias.data.repository

import com.lucasdias.core.resource.Resource
import com.lucasdias.data.BuildConfig.CAT_API_KEY
import com.lucasdias.data.mapper.toDomain
import com.lucasdias.data.repository.remote.CatService
import com.lucasdias.domain.enum.AnimalType
import com.lucasdias.domain.model.Animal
import com.lucasdias.domain.repository.SearchCatByNameRepository
import io.reactivex.rxjava3.core.Observable

class SearchCatByNameRepositoryImpl(
    private val service: CatService
) : SearchCatByNameRepository {

    //TODO: Implement .startWith
    override fun fetch(name: String): Observable<Resource<List<Animal>>> {
        return service.fetchByName(CAT_API_KEY, name)
            .toObservable()
            .map {
                it.toDomain(AnimalType.CAT)
            }
            .onErrorReturn {
                Resource.Error(it)
            }
    }
}
