package com.lucasdias.feature_animal.list

import androidx.annotation.VisibleForTesting
import com.lucasdias.base.presentation.BaseViewModel
import com.lucasdias.core.resource.Resource
import com.lucasdias.core.scheduler.RequestSchedulers
import com.lucasdias.domain.enum.RequestType
import com.lucasdias.domain.model.Animal
import com.lucasdias.domain.usecase.SearchAnimalByNameAndType
import io.reactivex.rxjava3.core.Observable

class AnimalListViewModel(
    private val searchAnimalByNameAndType: SearchAnimalByNameAndType,
    schedulers: RequestSchedulers
) : BaseViewModel<List<Animal>>(schedulers) {

    @VisibleForTesting internal var searchText = ""
    @VisibleForTesting internal var requestType = RequestType.CAT

    override fun request(): Observable<Resource<List<Animal>>> {
        return searchAnimalByNameAndType(searchText, requestType)
    }

    fun setupRequest(searchText: String, requestType: RequestType) {
        this.searchText = searchText
        this.requestType = requestType
    }
}
