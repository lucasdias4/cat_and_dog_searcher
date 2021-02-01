package com.lucasdias.domain.repository

import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.model.Animal
import io.reactivex.rxjava3.core.Observable

interface SearchDogByNameRepository {
    fun fetch(name: String): Observable<Resource<List<Animal>>>
}
