package com.lucasdias.feature_animal.di

import com.lucasdias.domain.usecase.SearchAnimalByNameAndType
import com.lucasdias.feature_animal.list.AnimalListViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val animalModule = module {

    viewModel {
        AnimalListViewModel(
            get<SearchAnimalByNameAndType>(),
            get<CoroutineDispatcher>()
        )
    }
}
