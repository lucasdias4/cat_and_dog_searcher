package com.lucasdias.domain.di

import com.lucasdias.domain.repository.SearchCatByNameRepository
import com.lucasdias.domain.repository.SearchDogByNameRepository
import com.lucasdias.domain.usecase.SearchAnimalByNameAndType
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val domainModule = module {
    factory {
        SearchAnimalByNameAndType(
            get<SearchCatByNameRepository>(),
            get<SearchDogByNameRepository>()
        )
    }
}
