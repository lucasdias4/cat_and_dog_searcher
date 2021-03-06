package com.lucasdias.data.mapper

import com.lucasdias.core.resource.Resource
import com.lucasdias.data.BuildConfig.CAT_IMAGE_URL
import com.lucasdias.data.BuildConfig.DOG_IMAGE_URL
import com.lucasdias.data.repository.remote.model.AnimalResponse
import com.lucasdias.domain.enum.AnimalType
import com.lucasdias.domain.model.Animal

fun Resource<List<AnimalResponse>>.toDomain(animalType: AnimalType): Resource<List<Animal>> {
    return when (this) {
        is Resource.Success -> this.toDomain(animalType)
        is Resource.SuccessWithoutContent -> Resource.SuccessWithoutContent()
        is Resource.Error -> Resource.Error(this.error())
        is Resource.Loading -> Resource.Loading()
    }
}

private fun Resource.Success<List<AnimalResponse>>.toDomain(animalType: AnimalType): Resource.Success<List<Animal>> {
    return Resource.Success(this.value().map { it.toDomain(animalType) })
}

private fun AnimalResponse.toDomain(animalType: AnimalType): Animal {
    return Animal(
        this.id,
        this.name,
        this.temperament,
        animalType.getImageUrlByAnimalType(this.imageId),
        this.lifetime,
        this.wikipediaUrl,
        animalType
    )
}

private fun AnimalType.getImageUrlByAnimalType(imageId: String?): String {
    return when (this) {
        AnimalType.CAT -> "$CAT_IMAGE_URL$imageId.jpg"
        else -> "$DOG_IMAGE_URL$imageId.jpg"
    }
}
