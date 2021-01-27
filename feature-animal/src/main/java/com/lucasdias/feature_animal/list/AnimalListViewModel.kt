package com.lucasdias.feature_animal.list

import com.lucasdias.base.presentation.BaseViewModel
import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.model.Animal
import com.lucasdias.domain.usecase.SearchAnimalByNameAndType
import kotlinx.coroutines.CoroutineDispatcher

class AnimalListViewModel(
    private val searchAnimalByNameAndType: SearchAnimalByNameAndType,
    coroutineContext: CoroutineDispatcher
) : BaseViewModel<List<Animal>>(coroutineContext) {

    override suspend fun request(): Resource<List<Animal>> {
        return searchAnimalByNameAndType("American")
    }
}
