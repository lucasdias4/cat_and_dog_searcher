package com.lucasdias.domain.repository

import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.model.Animal

interface SearchCatByNameRepository {
    suspend fun fetch(name: String): Resource<List<Animal>>
}
