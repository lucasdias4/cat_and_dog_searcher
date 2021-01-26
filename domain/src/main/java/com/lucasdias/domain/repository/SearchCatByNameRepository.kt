package com.lucasdias.domain.repository

import com.lucasdias.core.resource.Resource

interface SearchCatByNameRepository {
    suspend fun fetch(name: String): Resource<Any?>
}
