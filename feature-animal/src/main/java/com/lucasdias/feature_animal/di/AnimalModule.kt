package com.lucasdias.feature_animal.di

import com.lucasdias.core.connectivity.Connectivity
import com.lucasdias.core.di.CONNECTIVITY
import com.lucasdias.core.scheduler.RequestSchedulers
import com.lucasdias.domain.model.Animal
import com.lucasdias.domain.usecase.SearchAnimalByNameAndType
import com.lucasdias.feature_animal.list.AnimalListAdapter
import com.lucasdias.feature_animal.list.AnimalListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val animalModule = module {

    viewModel {
        AnimalListViewModel(
            get<SearchAnimalByNameAndType>(),
            get<Connectivity>(named(CONNECTIVITY)),
            get<RequestSchedulers>()
        )
    }

    factory { (navigateToAnimalDetail: ((Animal) -> Unit)) ->
        AnimalListAdapter(
            navigateToAnimalDetail
        )
    }
}
