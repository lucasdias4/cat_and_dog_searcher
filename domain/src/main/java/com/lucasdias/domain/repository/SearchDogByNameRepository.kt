package com.lucasdias.domain.repository

import com.lucasdias.core.resource.Resource

interface SearchDogByNameRepository {
    suspend fun fetch(name: String): Resource<Any?>
}
